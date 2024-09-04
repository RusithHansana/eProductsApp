package tech.eproducts.user_management_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.eproducts.user_management_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  boolean existsByEmail(String email);
}
