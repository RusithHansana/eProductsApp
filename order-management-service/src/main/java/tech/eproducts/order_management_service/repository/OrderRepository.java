package tech.eproducts.order_management_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.eproducts.order_management_service.model.Order;

/**
 * Repository interface for managing Order entities in MongoDB.
 *
 * This interface extends the MongoRepository interface provided by Spring Data MongoDB,
 * which includes methods for CRUD operations and query methods for the Order entity.
 */
public interface OrderRepository extends MongoRepository<Order, String> {
    // No additional methods are defined here, as MongoRepository provides basic CRUD operations
    // such as save(), findById(), findAll(), deleteById(), etc.
}