package tech.eproducts.order_management_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.eproducts.order_management_service.model.Order;
import tech.eproducts.order_management_service.service.OrderService;

import java.util.List;

/**
 * REST controller for handling order-related requests.
 * Maps incoming HTTP requests to appropriate methods in the OrderService.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired // Injects an instance of OrderService
    private OrderService orderService;

    /**
     * Retrieves all orders.
     *
     * @return List of all orders
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders(); // Fetch all orders
        return ResponseEntity.ok(orders); // Return 200 OK with the list of orders
    }

    /**
     * Retrieves a specific order by its ID.
     *
     * @param id The ID of the order to retrieve
     * @return ResponseEntity containing the order if found, or a 404 Not Found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if ID is invalid
        }
        return orderService.getOrderById(id); // Delegates to the OrderService to fetch the order by ID
    }

    /**
     * Creates a new order.
     *
     * @param order The order object to create
     * @return ResponseEntity containing the created order
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (order == null) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if order is null
        }
        Order createdOrder = orderService.createOrder(order); // Create a new order
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder); // Return 201 Created with the created order
    }

    /**
     * Updates an existing order.
     *
     * @param id The ID of the order to update
     * @param order The updated order object
     * @return ResponseEntity containing the updated order if successful, or a 404 Not Found response
     */
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order order) {
        if (id == null || id.isEmpty() || order == null) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if ID or order is invalid
        }
        return orderService.updateOrder(id, order); // Delegates to the OrderService to update the order
    }

    /**
     * Deletes an order.
     *
     * @param id The ID of the order to delete
     * @return ResponseEntity with 204 No Content if successful, or a 404 Not Found response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if ID is invalid
        }
        return orderService.deleteOrder(id); // Delegates to the OrderService to delete the order
    }
}