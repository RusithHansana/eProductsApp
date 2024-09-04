package tech.eproducts.product_catalog_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.eproducts.product_catalog_service.model.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {
}