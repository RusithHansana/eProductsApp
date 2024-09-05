package tech.eproducts.user_management_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import tech.eproducts.user_management_service.exception.ResourceNotFoundException;
import tech.eproducts.user_management_service.model.User;
import tech.eproducts.user_management_service.payload.*;
import tech.eproducts.user_management_service.repository.UserRepository;
import tech.eproducts.user_management_service.security.CurrentUser;
import tech.eproducts.user_management_service.security.JwtTokenProvider;
import tech.eproducts.user_management_service.security.UserPrincipal;
import tech.eproducts.user_management_service.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserService userService;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest,
      HttpServletResponse response) {
    User user = new User(registerRequest.getName(), registerRequest.getEmail(),
        passwordEncoder.encode(registerRequest.getPassword()), "USER", LocalDateTime.now());
    userRepository.save(user);

    String jwt = jwtTokenProvider.generateToken(user);
    addJwtCookie(response, jwt);

    return ResponseEntity.ok(new UserResponse(user));
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
    User user = userRepository.findByEmail(loginRequest.getEmail());
    if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      String jwt = jwtTokenProvider.generateToken(user);
      addJwtCookie(response, jwt);
      return ResponseEntity.ok(new UserResponse(user));
    }
    return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid email or password"));
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateUser(@CurrentUser UserPrincipal currentUser,
      @Valid @RequestBody UpdateUserRequest updateUserRequest) {
    User user = userRepository.findById(currentUser.getId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));

    if (updateUserRequest.getName() != null) {
      user.setName(updateUserRequest.getName());
    }
    if (updateUserRequest.getEmail() != null) {
      user.setEmail(updateUserRequest.getEmail());
    }
    if (updateUserRequest.getPassword() != null) {
      user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
    }

    userRepository.save(user);

    return ResponseEntity.ok(new UserResponse(user));
  }

  @GetMapping("/admin/users")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    List<UserResponse> userResponses = users.stream()
        .map(UserResponse::new)
        .collect(Collectors.toList());
    return ResponseEntity.ok(userResponses);
  }

  @GetMapping("/admin/{userId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
    UserResponse user = userService.getUserById(userId);
    return ResponseEntity.ok(user);
  }

  @PutMapping("/admin/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
      @RequestBody UpdateUserRequest updateUserRequest) {
    UserResponse updatedUser = userService.updateUser(id, updateUserRequest);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/admin/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(new ApiResponse(true, "User deleted successfully"));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser(HttpServletResponse response) {
    Cookie cookie = new Cookie("jwt", null);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setMaxAge(0);
    response.addCookie(cookie);

    return ResponseEntity.ok(new ApiResponse(true, "User logged out successfully"));
  }

  private void addJwtCookie(HttpServletResponse response, String jwt) {
    Cookie cookie = new Cookie("jwt", jwt);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setMaxAge(24 * 60 * 60); // 24 hours
    response.addCookie(cookie);
  }
}
