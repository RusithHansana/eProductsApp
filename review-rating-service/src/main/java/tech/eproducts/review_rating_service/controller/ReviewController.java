package tech.eproducts.review_rating_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.eproducts.review_rating_service.model.Review;
import tech.eproducts.review_rating_service.service.ReviewService;

import java.util.List;

/**
 * REST controller for handling review-related requests.
 * Maps incoming HTTP requests to appropriate methods in the ReviewService.
 */
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired // Injects an instance of ReviewService
    private ReviewService reviewService;

    /**
     * Retrieves all reviews.
     *
     * @return List of all reviews
     */
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews(); // Delegates to the ReviewService to fetch all reviews
    }

    /**
     * Retrieves a specific review by its ID.
     *
     * @param id The ID of the review to retrieve
     * @return ResponseEntity containing the review if found, or a 404 Not Found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        return reviewService.getReviewById(id); // Delegates to the ReviewService to fetch the review by ID
    }

    /**
     * Creates a new review.
     *
     * @param review The review object to create
     * @return The created review
     */
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewService.addReviewToProduct(review); // Delegates to the ReviewService to create a new review
    }

    /**
     * Updates an existing review.
     *
     * @param id The ID of the review to update
     * @param review The updated review object
     * @return ResponseEntity containing the updated review if successful, or a 404 Not Found response
     */
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable String id, @RequestBody Review review) {
        return reviewService.updateReview(id, review); // Delegates to the ReviewService to update the review
    }

    /**
     * Deletes a review.
     *
     * @param id The ID of the review to delete
     * @return ResponseEntity with 204 No Content if successful, or a 404 Not Found response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        return reviewService.deleteReview(id); // Delegates to the ReviewService to delete the review
    }
}