package tech.eproducts.order_management_service.service;

import org.springframework.http.ResponseEntity;
import tech.eproducts.order_management_service.model.Order;

import java.util.List;

/**
 * Service interface for handling order-related operations.
 */
public interface OrderService {
//    add impl package and more orderserviceimpl pakcage
//            add excpetions

    /**
     * Retrieves all orders.
     *
     * @return List of all orders
     */
    List<Order> getAllOrders();

    /**
     * Retrieves a specific order by its ID.
     *
     * @param id The ID of the order to retrieve
     * @return ResponseEntity containing the order if found, or a 404 Not Found response
     */
    ResponseEntity<Order> getOrderById(String id);

    /**
     * Creates a new order.
     *
     * @param order The order object to create
     * @return The created order
     */
    Order createOrder(Order order);

    /**
     * Updates an existing order.
     *
     * @param id The ID of the order to update
     * @param order The updated order object
     * @return ResponseEntity containing the updated order if successful, or a 404 Not Found response
     */
    ResponseEntity<Order> updateOrder(String id, Order order);

    /**
     * Deletes an order.
     *
     * @param id The ID of the order to delete
     * @return ResponseEntity with 204 No Content if successful, or a 404 Not Found response
     */
    ResponseEntity<Void> deleteOrder(String id);
}