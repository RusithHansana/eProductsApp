package tech.eproducts.review_rating_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.eproducts.review_rating_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  boolean existsByEmail(String email);
}
