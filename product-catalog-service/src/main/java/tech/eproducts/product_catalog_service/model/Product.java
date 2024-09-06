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
import java.util.ArrayList;
import java.util.List;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    private List<Review> reviews = new ArrayList<>(); // Initializes the reviews list

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

    public Product(String name, String image, String brand, String category, String description) {
        this.name = name;
        this.image = image;
        this.brand = brand;
        this.category = category;
        this.description = description;
    }
}