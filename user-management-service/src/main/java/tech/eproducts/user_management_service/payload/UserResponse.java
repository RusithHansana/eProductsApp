package tech.eproducts.user_management_service.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import tech.eproducts.user_management_service.model.User;

/**
 * Represents a response object for user data.
 * This class is used to transfer user information from the service layer to the client.
 */
public class UserResponse {
  private Long _id;
  private String name;
  private String email;

  @JsonProperty("isAdmin")
  private boolean isAdmin;

  /**
   * Constructs a UserResponse object from a User entity.
   *
   * @param user The User entity to construct the response from.
   */
  public UserResponse(User user) {
    this._id = user.get_id();
    this.name = user.getName();
    this.email = user.getEmail();
    this.isAdmin = "ADMIN".equals(user.getRole());
  }

  /**
   * Creates a UserResponse object from a User entity.
   *
   * @param user The User entity to create the response from.
   * @return A new UserResponse object.
   */
  public static UserResponse fromUser(User user) {
    return new UserResponse(user);
  }

  /**
   * Gets the user's ID.
   *
   * @return The user's ID.
   */
  public Long get_id() {
    return _id;
  }

  /**
   * Gets the user's name.
   *
   * @return The user's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the user's email.
   *
   * @return The user's email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Checks if the user is an admin.
   *
   * @return true if the user is an admin, false otherwise.
   */
  @JsonProperty("isAdmin")
  public boolean isAdmin() {
    return isAdmin;
  }
}
