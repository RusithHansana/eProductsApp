package tech.eproducts.review_rating_service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.eproducts.review_rating_service.model.Product;
import tech.eproducts.review_rating_service.model.Review;
import tech.eproducts.review_rating_service.repository.ReviewRepository;
import tech.eproducts.review_rating_service.repository.ProductRepository;
import tech.eproducts.review_rating_service.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public ResponseEntity<Review> getReviewById(String id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public Review addReviewToProduct(Review newReview) {
        // Find the product by ID using the repository
        Product product = productRepository.findById(newReview.getProductId())
                .orElseThrow(() -> new RuntimeException("Product Not Found")); // Throws an exception if the product is not found

        // Set the createdAt and updatedAt fields for the new review
        newReview.setCreatedAt(LocalDateTime.now()); // Sets the creation timestamp
        newReview.setUpdatedAt(LocalDateTime.now()); // Sets the last modified timestamp

        Review review = reviewRepository.save(newReview);
        // Add the new review to the product's list of reviews
        product.addReview(review);

        // Save the updated product back to the database
        productRepository.save(product); // Persist the changes to the database

        // Return a success response
        return review;
    }

    @Override
    public ResponseEntity<Review> updateReview(String id, Review review) {
        // Find the existing review by ID
        Optional<Review> existingReviewOptional = reviewRepository.findById(id);
        if (!existingReviewOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Return 404 if the review is not found
        }

        Review existingReview = existingReviewOptional.get(); // Get the existing review

        // Update only the fields that need to be
        existingReview.setUserName(review.getUserName());
        existingReview.setComment(review.getComment()); // Update the comment
        existingReview.setRating(review.getRating()); // Update the rating
        existingReview.setUpdatedAt(LocalDateTime.now()); // Set the updated timestamp

        // Save the updated review in the review repository
        Review updatedReview = reviewRepository.save(existingReview);

        // Now, update the product's review list
        Product product = productRepository.findById(existingReview.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found")); // Find the associated product

        // Update the product's review in its list
        product.getReviews().stream()
                .filter(r -> r.getId().equals(updatedReview.getId())) // Find the review in the product's list
                .findFirst()
                .ifPresent(r -> {
                    r.setComment(updatedReview.getComment()); // Update the comment in the product's review
                    r.setRating(updatedReview.getRating()); // Update the rating in the product's review
                    r.setUpdatedAt(updatedReview.getUpdatedAt()); // Update the timestamp in the product's review
                });

        // Save the updated product back to the database
        productRepository.save(product); // Persist the changes to the database

        // Return the updated review
        return ResponseEntity.ok(updatedReview);
    }

    @Override
    public ResponseEntity<Void> deleteReview(String id) {
        // Find the existing review by ID
        Optional<Review> existingReviewOptional = reviewRepository.findById(id);
        if (!existingReviewOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Return 404 if the review is not found
        }

        Review existingReview = existingReviewOptional.get(); // Get the existing review
        String productId = existingReview.getProductId(); // Get the associated product ID

        // Find the associated product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found")); // Handle case where product is not found

        // Remove the review from the product's list of reviews
        product.removeReview(id); // Remove the review by ID

        // Save the updated product back to the database
        productRepository.save(product); // Persist the changes to the product

        // Delete the review from the review repository
        reviewRepository.deleteById(id); // Delete the review from the review repository

        return ResponseEntity.noContent().build(); // Return 204 No Content to indicate successful deletion
    }
}