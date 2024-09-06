package tech.eproducts.payment_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PaymentRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String paypalPaymentId;
  private Double amount;
  private String currency;
  private String method;
  private String description;
  private String status;
}
