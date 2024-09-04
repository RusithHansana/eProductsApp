package tech.eproducts.user_management_service.payload;

import tech.eproducts.user_management_service.model.User;

public class UserResponse {
  private Long _id;
  private String name;
  private String email;
  private String role;

  public UserResponse(User user) {
    this._id = user.get_id();
    this.name = user.getName();
    this.email = user.getEmail();
    this.role = user.getRole();
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

  public String getRole() {
    return role;
  }
}
