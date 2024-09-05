package tech.eproducts.product_catalog_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// Represents a review for a product
@Document(collection = "reviews") // Maps this class to the "reviews" collection in MongoDB
@Data // Generates getters, setters, and other utility methods
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
public class Review {

    @Id // Marks this field as the primary key in the MongoDB document
    private String id; // Unique identifier for the review

    private String productId; // Reference to the ID of the product being reviewed

    private String userName;
    private String comment; // The text of the review comment
    private Double rating; // The rating given in the review (e.g., 1.0 to 5.0)

    @CreatedDate // Automatically populated with the current date when the review is created
    private LocalDateTime createdAt; // Timestamp of when the review was created

    @LastModifiedDate // Automatically updated with the current date when the review is modified
    private LocalDateTime updatedAt; // Timestamp of the last modification to the review
}