package tech.eproducts.order_management_service.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.eproducts.order_management_service.model.Order;
import tech.eproducts.order_management_service.repository.OrderRepository;
import tech.eproducts.order_management_service.service.OrderService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the OrderService interface.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Retrieves all orders from the repository.
     *
     * @return List of all orders
     */
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order
     * @return ResponseEntity containing the order if found, otherwise a 404 Not Found response
     */
    @Override
    public ResponseEntity<Order> getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Creates a new order.
     *
     * @param order The order to be created
     * @return The created order
     */
    @Override
    public Order createOrder(Order order) {
        Order newOrder = new Order(
                order.getProducts(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getUser(),
                order.getShippingAddress(),
                order.getPaymentMethod(),
                order.getItemsPrice(),
                order.getShippingPrice(),
                order.getTaxPrice()
        );
        return orderRepository.save(newOrder);
    }

    /**
     * Updates an existing order.
     *
     * @param id    The ID of the order to be updated
     * @param order The updated order details
     * @return ResponseEntity containing the updated order if found, otherwise a 404 Not Found response
     */
    @Override
    public ResponseEntity<Order> updateOrder(String id, Order order) {
        if (orderRepository.existsById(id)) {
            order.setId(id);
            Order updatedOrder = orderRepository.save(order);
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to be deleted
     * @return ResponseEntity with a 204 No Content status if the order was deleted, otherwise a 404 Not Found response
     */
    @Override
    public ResponseEntity<Void> deleteOrder(String id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}