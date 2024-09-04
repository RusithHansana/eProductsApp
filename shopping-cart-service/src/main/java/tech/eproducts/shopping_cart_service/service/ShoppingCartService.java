package tech.eproducts.shopping_cart_service.service;

import org.springframework.http.ResponseEntity;
import tech.eproducts.shopping_cart_service.model.ShoppingCart;

import java.util.List;

/**
 * Service interface for managing shopping carts.
 * Defines the methods for cart operations such as retrieving,
 * creating, updating, and deleting shopping carts.
 */
public interface ShoppingCartService {

    /**
     * Retrieves all shopping carts.
     *
     * @return List of ShoppingCart objects
     */
    List<ShoppingCart> getAllCarts();

    /**
     * Retrieves a specific shopping cart by its ID.
     *
     * @param id Unique identifier of the shopping cart
     * @return ResponseEntity containing the ShoppingCart object or a not found response
     */
    ResponseEntity<ShoppingCart> getCartById(String id);

    /**
     * Creates a new shopping cart.
     *
     * @param cart ShoppingCart object to be created
     * @return The created ShoppingCart object
     */
    ShoppingCart createCart(ShoppingCart cart);

    /**
     * Updates an existing shopping cart.
     *
     * @param id Unique identifier of the shopping cart to update
     * @param cart ShoppingCart object with updated information
     * @return ResponseEntity containing the updated ShoppingCart object or a not found response
     */
    ResponseEntity<ShoppingCart> updateCart(String id, ShoppingCart cart);

    /**
     * Deletes a specific shopping cart by its ID.
     *
     * @param id Unique identifier of the shopping cart to delete
     * @return ResponseEntity with no content or a not found response
     */
    ResponseEntity<Void> deleteCart(String id);
}