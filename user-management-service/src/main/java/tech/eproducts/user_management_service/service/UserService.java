package tech.eproducts.user_management_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.eproducts.user_management_service.exception.ResourceNotFoundException;
import tech.eproducts.user_management_service.model.User;
import tech.eproducts.user_management_service.payload.UpdateUserRequest;
import tech.eproducts.user_management_service.payload.UserResponse;
import tech.eproducts.user_management_service.repository.UserRepository;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * Retrieves all users from the database.
   *
   * @return A list of all users.
   */
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * Retrieves a user by their ID.
   *
   * @param userId The ID of the user to retrieve.
   * @return A UserResponse object containing the user's information.
   * @throws ResourceNotFoundException if the user is not found.
   */
  public UserResponse getUserById(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    return UserResponse.fromUser(user);
  }

  /**
   * Updates a user's information.
   *
   * @param id The ID of the user to update.
   * @param updateUserRequest The request object containing the updated user information.
   * @return A UserResponse object containing the updated user's information.
   * @throws ResourceNotFoundException if the user is not found.
   */
  public UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

    if (updateUserRequest.getName() != null) {
      user.setName(updateUserRequest.getName());
    }
    if (updateUserRequest.getEmail() != null) {
      user.setEmail(updateUserRequest.getEmail());
    }
    if (updateUserRequest.isAdmin() != null) {
      user.setRole(updateUserRequest.isAdmin() ? "ADMIN" : "USER");
    }

    User updatedUser = userRepository.save(user);
    return UserResponse.fromUser(updatedUser);
  }

  /**
   * Deletes a user from the database.
   *
   * @param id The ID of the user to delete.
   * @throws ResourceNotFoundException if the user is not found.
   */
  public void deleteUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    userRepository.delete(user);
  }

}
