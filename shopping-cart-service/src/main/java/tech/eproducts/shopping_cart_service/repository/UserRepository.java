package tech.eproducts.shopping_cart_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.eproducts.shopping_cart_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  boolean existsByEmail(String email);
}
