package tech.eproducts.product_catalog_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.eproducts.product_catalog_service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}