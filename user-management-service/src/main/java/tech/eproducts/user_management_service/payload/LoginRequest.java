package tech.eproducts.user_management_service.payload;

/**
 * Represents a login request containing user credentials.
 */
public class LoginRequest {
  private String email;
  private String password;

  /**
   * Gets the email address of the user.
   *
   * @return The email address.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the user.
   *
   * @param email The email address to set.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the password of the user.
   *
   * @return The password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the user.
   *
   * @param password The password to set.
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
