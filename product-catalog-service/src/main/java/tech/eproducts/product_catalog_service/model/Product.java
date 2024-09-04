package tech.eproducts.product_catalog_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;

//    @DBRef
//    private User user; // Assuming you have a User class

    @DBRef
    private List<Review> reviews;

    private String name;
    private String image;
    private String brand;
    private String category;
    private String description;
    private Double rating = 0.0;
    private Integer numReviews = 0;
    private Double price = 0.0;
    private Integer countInStock = 0;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // Constructors, getters, and setters

}