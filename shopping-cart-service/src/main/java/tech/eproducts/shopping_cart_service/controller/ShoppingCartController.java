package tech.eproducts.shopping_cart_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.eproducts.shopping_cart_service.model.ShoppingCart;
import tech.eproducts.shopping_cart_service.service.ShoppingCartService;

import java.util.List;

/**
 * Controller class for managing shopping cart operations.
 */
@RestController
@RequestMapping("/api/carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Retrieves all shopping carts.
     *
     * @return List of all shopping carts
     */
    @GetMapping
    public List<ShoppingCart> getAllCarts() {
        return shoppingCartService.getAllCarts();
    }

    /**
     * Retrieves a specific cart by ID.
     *
     * @param id The ID of the shopping cart
     * @return ResponseEntity containing the shopping cart if found, or not found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable String id) {
        return shoppingCartService.getCartById(id);
    }

    /**
     * Creates a new shopping cart.
     *
     * @param cart The shopping cart to be created
     * @return The created shopping cart
     */
    @PostMapping
    public ShoppingCart createCart(@RequestBody ShoppingCart cart) {
        return shoppingCartService.createCart(cart);
    }

    /**
     * Updates an existing shopping cart.
     *
     * @param id The ID of the shopping cart to be updated
     * @param cart The updated shopping cart information
     * @return ResponseEntity containing the updated shopping cart if found, or not found status
     */
    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateCart(@PathVariable String id, @RequestBody ShoppingCart cart) {
        return shoppingCartService.updateCart(id, cart);
    }

    /**
     * Deletes a specific shopping cart by ID.
     *
     * @param id The ID of the shopping cart to be deleted
     * @return ResponseEntity with no content if successful, or not found status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        return shoppingCartService.deleteCart(id);
    }
}