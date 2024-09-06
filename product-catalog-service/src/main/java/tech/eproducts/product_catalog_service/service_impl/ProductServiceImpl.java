package tech.eproducts.product_catalog_service.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.eproducts.product_catalog_service.model.Product;
import tech.eproducts.product_catalog_service.repository.ProductRepository;
import tech.eproducts.product_catalog_service.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductService interface.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieves all products from the repository.
     *
     * @return List of all products
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product
     * @return ResponseEntity containing the product if found, otherwise a 404 Not Found response
     */
    @Override
    public ResponseEntity<Product> getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new product.
     *
     * @return The created product
     */
    @Override
    public Product createProduct() {
        Product product = new Product(
                "Product_Name",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXgz9Emvr38Z0a31MnbUXMCw62qAbQm3GiNw&s",
                "Brand_Name",
                "Category",
                "Please update the product details to reflect the correct information"
        );

        return productRepository.save(product);
    }

    /**
     * Updates an existing product.
     *
     * @param id      The ID of the product to be updated
     * @param product The updated product details
     * @return ResponseEntity containing the updated product if found, otherwise a 404 Not Found response
     */
    @Override
    public ResponseEntity<Product> updateProduct(String id, Product product) {
        Optional<Product> existingProductOptional = productRepository.findById(id);

        // Check if the product exists
        if (!existingProductOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Product existingProduct = existingProductOptional.get();

        // Update only the fields that need to be changed
        existingProduct.setName(product.getName());
        existingProduct.setImage(product.getImage());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setReviews(product.getReviews());
        existingProduct.setRating(product.getRating());
        existingProduct.setNumReviews(product.getNumReviews());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCountInStock(product.getCountInStock());

        // Update the last modified timestamp
        existingProduct.setUpdatedAt(LocalDateTime.now());

        // Save the updated product and return it
        return ResponseEntity.ok(productRepository.save(existingProduct));
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to be deleted
     * @return ResponseEntity with a 204 No Content status if the product was deleted, otherwise a 404 Not Found response
     */
    @Override
    public ResponseEntity<Void> deleteProduct(String id) {
        // Check if the product exists before attempting to delete
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}