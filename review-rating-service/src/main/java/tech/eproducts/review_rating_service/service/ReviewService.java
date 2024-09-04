package tech.eproducts.review_rating_service.service;

import org.springframework.http.ResponseEntity;
import tech.eproducts.review_rating_service.model.Review;

import java.util.List;

public interface ReviewService {

        /**
         * Retrieves all reviews from the database.
         *
         * @return List of all reviews.
         */
        List<Review> getAllReviews();

        /**
         * Retrieves a specific review by its ID.
         *
         * @param id The ID of the review to retrieve.
         * @return ResponseEntity containing the review if found, or a 404 Not Found response.
         */
        ResponseEntity<Review> getReviewById(String id);

        /**
         * Creates a new review and saves it to the database.
         *
         * @param review The review object to create.
         * @return The saved review object.
         */
        Review createReview(Review review);

        /**
         * Updates an existing review identified by its ID.
         *
         * @param id     The ID of the review to update.
         * @param review The updated review object.
         * @return ResponseEntity containing the updated review if successful, or a 404 Not Found response.
         */
        ResponseEntity<Review> updateReview(String id, Review review);

        /**
         * Deletes a review identified by its ID.
         *
         * @param id The ID of the review to delete.
         * @return ResponseEntity with 204 No Content if successful, or a 404 Not Found response.
         */
        ResponseEntity<Void> deleteReview(String id);
}
