/**
 * Repository interface for managing User entities.
 * Extends JpaRepository to provide CRUD operations for User objects.
 */
package tech.eproducts.user_management_service.repository;

import tech.eproducts.user_management_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  /**
   * Finds a user by their email address.
   *
   * @param email The email address to search for.
   * @return The User object if found, or null if not found.
   */
  User findByEmail(String email);

  /**
   * Checks if a user with the given email exists.
   *
   * @param email The email address to check.
   * @return true if a user with the email exists, false otherwise.
   */
  boolean existsByEmail(String email);
}
