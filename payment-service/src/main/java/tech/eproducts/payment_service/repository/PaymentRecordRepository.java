package tech.eproducts.payment_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.eproducts.payment_service.model.PaymentRecord;

public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, Long> {
  List<PaymentRecord> findAllByOrderByIdDesc();
}
