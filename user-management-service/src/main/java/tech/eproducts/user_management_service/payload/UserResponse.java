package tech.eproducts.user_management_service.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import tech.eproducts.user_management_service.model.User;

public class UserResponse {
  private Long _id;
  private String name;
  private String email;

  @JsonProperty("isAdmin")
  private boolean isAdmin;

  public UserResponse(User user) {
    this._id = user.get_id();
    this.name = user.getName();
    this.email = user.getEmail();
    this.isAdmin = "ADMIN".equals(user.getRole());
  }

  public static UserResponse fromUser(User user) {
    return new UserResponse(user);
  }

  // Getters
  public Long get_id() {
    return _id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  @JsonProperty("isAdmin")
  public boolean isAdmin() {
    return isAdmin;
  }
}
