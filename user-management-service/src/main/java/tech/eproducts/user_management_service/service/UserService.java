package tech.eproducts.user_management_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.eproducts.user_management_service.exception.ResourceNotFoundException;
import tech.eproducts.user_management_service.model.User;
import tech.eproducts.user_management_service.payload.UpdateUserRequest;
import tech.eproducts.user_management_service.payload.UserResponse;
import tech.eproducts.user_management_service.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public UserResponse getUserById(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    return UserResponse.fromUser(user);
  }

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

  public void deleteUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    userRepository.delete(user);
  }

}
