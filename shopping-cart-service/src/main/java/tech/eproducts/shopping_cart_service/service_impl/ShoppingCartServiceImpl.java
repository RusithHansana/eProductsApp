package tech.eproducts.shopping_cart_service.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.eproducts.shopping_cart_service.model.ShoppingCart;
import tech.eproducts.shopping_cart_service.repository.ShoppingCartRepository;
import tech.eproducts.shopping_cart_service.service.ShoppingCartService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ShoppingCartService interface.
 * Contains the business logic for managing shopping carts,
 * including methods for retrieving, creating, updating, and deleting carts.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository; // Repository for accessing shopping cart data

    @Override
    public List<ShoppingCart> getAllCarts() {
        return shoppingCartRepository.findAll(); // Retrieves all shopping carts
    }

    @Override
    public ResponseEntity<ShoppingCart> getCartById(String id) {
        Optional<ShoppingCart> cart = shoppingCartRepository.findById(id); // Find cart by ID
        return cart.map(ResponseEntity::ok) // Return cart if found
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 if not found
    }

    @Override
    public ShoppingCart createCart(ShoppingCart cart) {
        cart.setCreatedAt(LocalDateTime.now()); // Set creation timestamp
        cart.setUpdatedAt(LocalDateTime.now()); // Set update timestamp
        return shoppingCartRepository.save(cart); // Saves the new shopping cart
    }

    @Override
    public ResponseEntity<ShoppingCart> updateCart(String id, ShoppingCart cart) {
        Optional<ShoppingCart> existingCartOptional = shoppingCartRepository.findById(id); // Find existing cart
        if (!existingCartOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }

        ShoppingCart existingCart = existingCartOptional.get(); // Get existing cart
        existingCart.setItems(cart.getItems()); // Update items in the cart
        existingCart.setUpdatedAt(LocalDateTime.now()); // Update the last modified timestamp

        return ResponseEntity.ok(shoppingCartRepository.save(existingCart)); // Save and return the updated cart
    }

    @Override
    public ResponseEntity<Void> deleteCart(String id) {
        if (!shoppingCartRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Return 404 if cart does not exist
        }
        shoppingCartRepository.deleteById(id); // Delete the cart by ID
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}