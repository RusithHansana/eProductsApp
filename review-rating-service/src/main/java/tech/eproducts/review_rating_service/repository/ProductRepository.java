package tech.eproducts.review_rating_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.eproducts.review_rating_service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}