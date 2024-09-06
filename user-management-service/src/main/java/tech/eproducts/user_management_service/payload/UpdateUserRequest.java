package tech.eproducts.user_management_service.payload;

/**
 * Represents a request to update user information.
 */
public class UpdateUserRequest {
  private String name;
  private String email;
  private String password;
  private boolean isAdmin;

  /**
   * Gets the name of the user.
   * @return The name of the user.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the user.
   * @param name The name to set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the email of the user.
   * @return The email of the user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the user.
   * @param email The email to set.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the password of the user.
   * @return The password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the user.
   * @param password The password to set.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Checks if the user is an admin.
   * @return True if the user is an admin, false otherwise.
   */
  public Boolean isAdmin() {
    return isAdmin;
  }

  /**
   * Sets the admin status of the user.
   * @param isAdmin The admin status to set.
   */
  public void setAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}
