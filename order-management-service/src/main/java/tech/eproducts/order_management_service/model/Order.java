package tech.eproducts.order_management_service.model;

// Lombok annotations for automatic generation of boilerplate code
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an order entity in the system.
 * This class is mapped to the "orders" collection in MongoDB.
 */
@Document(collection = "orders") // Specifies the MongoDB collection name
@Data // Lombok annotation to generate getters, setters, and other utility methods
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor // Generates a no-argument constructor
public class Order {

    @Id // Marks this field as the primary key in the MongoDB document
    private String id; // Unique identifier for the order

    @DBRef // Indicates that this field is a reference to another document (Product)
    private List<Product> products; // List of products included in the order

    private Double totalPrice; // Total price of the order
    private String status; // Status of the order (e.g., "Pending", "Shipped", "Delivered")

    private String user;
    private String shippingAddress; // Shipping address for the order
    private String paymentMethod; // Payment method used for the order
    private Double itemsPrice; // Total price of items in the order
    private Double shippingPrice; // Shipping cost for the order
    private Double taxPrice; // Tax applied to the order

    @CreatedDate // Automatically populated with the current date when the order is created
    private LocalDateTime createdAt; // Timestamp for when the order was created

    @LastModifiedDate // Automatically updated with the current date when the order is modified
    private LocalDateTime updatedAt; // Timestamp for when the order was last updated

    public Order(List<Product> products, Double totalPrice, String status, String user, String shippingAddress, String paymentMethod, Double itemsPrice, Double shippingPrice, Double taxPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
        this.status = status;
        this.user = user;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.itemsPrice = itemsPrice;
        this.shippingPrice = shippingPrice;
        this.taxPrice = taxPrice;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructors, getters, and setters are generated by Lombok annotations
}