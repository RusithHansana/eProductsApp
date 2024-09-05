package tech.eproducts.review_rating_service.model;

// Lombok annotations for automatic generation of boilerplate code

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Represents a product in the system
@Document(collection = "products") // Maps this class to the "products" collection in MongoDB
@Data // Generates getters, setters, and other utility methods
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor // Generates a no-argument constructor
public class Product {

    @Id // Marks this field as the primary key in the MongoDB document
    private String id; // Unique identifier for the product

    // List of reviews associated with this product
    private List<Review> reviews = new ArrayList<>(); // Initializes the reviews list

    private String name; // Name of the product
    private String image; // URL or path to the product image
    private String brand; // Brand of the product
    private String category; // Category to which the product belongs
    private String description; // Description of the product
    private Double rating = 0.0; // Average rating of the product (defaults to 0.0)
    private Integer numReviews = 0; // Number of reviews for the product (defaults to 0)
    private Double price = 0.0; // Price of the product
    private Integer countInStock = 0; // Number of items available in stock

    @CreatedDate // Automatically populated with the current date when the product is created
    private LocalDateTime createdAt; // Timestamp of when the product was created

    @LastModifiedDate // Automatically updated with the current date when the product is modified
    private LocalDateTime updatedAt; // Timestamp of the last modification to the product

    // Adds a review to the product and updates the rating
    public void addReview(Review review) {
        this.reviews.add(review); // Adds the new review to the list of reviews
        updateRating(); // Updates the product's rating based on the current reviews
    }

    // Removes a review from the product and updates the rating
    public void removeReview(String reviewId) {
        this.reviews.removeIf(review -> review.getId().equals(reviewId)); // Remove the review by ID
        updateRating(); // Update the rating after removing the review
    }

    // Updates the product's rating and number of reviews
    private void updateRating() {
        if (reviews != null && !reviews.isEmpty()) { // Check if there are any reviews
            double totalRating = reviews.stream().mapToDouble(Review::getRating).sum(); // Sum the ratings of all reviews
            this.rating = totalRating / reviews.size(); // Calculate the average rating
            this.numReviews = reviews.size(); // Update the total number of reviews
        } else {
            this.rating = 0.0; // Reset rating to 0 if there are no reviews
            this.numReviews = 0; // Reset number of reviews to 0
        }
    }
}