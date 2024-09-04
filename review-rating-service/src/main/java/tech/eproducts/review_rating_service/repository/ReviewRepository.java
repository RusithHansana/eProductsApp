package tech.eproducts.review_rating_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.eproducts.review_rating_service.model.Review;

/**
 * Repository interface for managing Review entities in MongoDB.
 *
 * This interface extends the MongoRepository interface provided by Spring Data MongoDB,
 * which includes methods for CRUD operations and query methods for the Review entity.
 */
public interface ReviewRepository extends MongoRepository<Review, String> {
    // No additional methods are defined here, as MongoRepository provides basic CRUD operations
    // such as save(), findById(), findAll(), deleteById(), etc.
}