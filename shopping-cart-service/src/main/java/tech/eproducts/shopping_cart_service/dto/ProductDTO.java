package tech.eproducts.shopping_cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for Product.
 * This class is used to transfer product data between layers
 * without exposing the internal model directly.
 */
@Data
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor // Generates a no-argument constructor
public class ProductDTO {
    private String id; // Unique identifier for the product
    private String name; // Name of the product
    private Double price; // Price of the product
    private String description; // Description of the product
}