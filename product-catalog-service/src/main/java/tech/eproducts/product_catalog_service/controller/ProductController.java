package tech.eproducts.product_catalog_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.eproducts.product_catalog_service.model.Product;
import tech.eproducts.product_catalog_service.service.ProductService;

import java.util.List;

/**
 * Controller class for handling product-related HTTP requests.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products.
     *
     * @return A list of all products
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve
     * @return ResponseEntity containing the product if found, or not found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    /**
     * Creates a new product. Only accessible to users with ADMIN role.
     *
     * @return The created product
     */
    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Product createProduct() {
        return productService.createProduct();
    }

    /**
     * Updates an existing product. Only accessible to users with ADMIN role.
     *
     * @param id The ID of the product to update
     * @param product The updated product information
     * @return ResponseEntity containing the updated product if successful, or appropriate error status
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    /**
     * Deletes a product by its ID. Only accessible to users with ADMIN role.
     *
     * @param id The ID of the product to delete
     * @return ResponseEntity with no content if successful, or appropriate error status
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}