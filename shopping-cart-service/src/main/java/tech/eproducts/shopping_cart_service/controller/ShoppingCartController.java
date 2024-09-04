package tech.eproducts.shopping_cart_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.eproducts.shopping_cart_service.model.ShoppingCart;
import tech.eproducts.shopping_cart_service.service.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    public List<ShoppingCart> getAllCarts() {
        return shoppingCartService.getAllCarts(); // Retrieves all shopping carts
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable String id) {
        return shoppingCartService.getCartById(id); // Retrieves a specific cart by ID
    }

    @PostMapping
    public ShoppingCart createCart(@RequestBody ShoppingCart cart) {
        return shoppingCartService.createCart(cart); // Creates a new shopping cart
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateCart(@PathVariable String id, @RequestBody ShoppingCart cart) {
        return shoppingCartService.updateCart(id, cart); // Updates an existing shopping cart
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        return shoppingCartService.deleteCart(id); // Deletes a specific shopping cart by ID
    }
}
