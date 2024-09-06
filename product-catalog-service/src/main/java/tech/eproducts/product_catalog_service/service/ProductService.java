package tech.eproducts.product_catalog_service.service;

import org.springframework.http.ResponseEntity;
import tech.eproducts.product_catalog_service.model.Product;

import java.util.List;

public interface ProductService {

    /**
     * Retrieves all products.
     *
     * @return List of all products
     */
    List<Product> getAllProducts();

    /**
     * Retrieves a specific product by its ID.
     *
     * @param id The ID of the product to retrieve
     * @return ResponseEntity containing the product if found, or a 404 Not Found response
     */
    ResponseEntity<Product> getProductById(String id);

    /**
     * Creates a new product.
     *
     * @return The created product
     */
    Product createProduct();

    /**
     * Updates an existing product.
     *
     * @param id The ID of the product to update
     * @param product The updated product object
     * @return ResponseEntity containing the updated product if successful, or a 404 Not Found response
     */
    ResponseEntity<Product> updateProduct(String id, Product product);

    /**
     * Deletes a product.
     *
     * @param id The ID of the product to delete
     * @return ResponseEntity with 204 No Content if successful, or a 404 Not Found response
     */
    ResponseEntity<Void> deleteProduct(String id);
}