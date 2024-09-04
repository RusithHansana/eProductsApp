package tech.eproducts.review_rating_service.service_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.eproducts.review_rating_service.model.Review;
import tech.eproducts.review_rating_service.repository.ReviewRepository;
import tech.eproducts.review_rating_service.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

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
    public Review createReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public ResponseEntity<Review> updateReview(String id, Review review) {
        Optional<Review> existingReviewOptional = reviewRepository.findById(id);
        if (!existingReviewOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Review existingReview = existingReviewOptional.get();

        // Update only the fields that need to be changed
        existingReview.setComment(review.getComment());
        existingReview.setRating(review.getRating());
        existingReview.setUpdatedAt(LocalDateTime.now());

        // Save the updated review
        return ResponseEntity.ok(reviewRepository.save(existingReview));
    }

    @Override
    public ResponseEntity<Void> deleteReview(String id) {
        if (!reviewRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reviewRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}