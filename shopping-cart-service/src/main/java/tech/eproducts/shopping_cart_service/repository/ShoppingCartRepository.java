package tech.eproducts.shopping_cart_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.eproducts.shopping_cart_service.model.ShoppingCart;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
    // Additional query methods can be defined here if needed
}
