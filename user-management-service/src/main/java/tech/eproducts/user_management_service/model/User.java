package tech.eproducts.user_management_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a user in the system.
 * This entity is mapped to the "users" table in the database.
 */
@Entity
@Table(name = "users")
public class User {
  /**
   * The unique identifier for the user.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long _id;

  /**
   * The name of the user.
   */
  @Column(nullable = false)
  private String name;

  /**
   * The email address of the user. This must be unique.
   */
  @Column(nullable = false, unique = true)
  private String email;

  /**
   * The password of the user.
   */
  @Column(nullable = false)
  private String password;

  /**
   * The role of the user in the system.
   */
  @Column(nullable = false)
  private String role;

  /**
   * The timestamp when the user was created or last updated.
   */
  @Column(nullable = false)
  private LocalDateTime timestamp;

  /**
   * Default constructor for the User class.
   */
  public User() {
    // Default constructor
  }

  /**
   * Constructs a new User with the specified details.
   *
   * @param name      The name of the user.
   * @param email     The email address of the user.
   * @param password  The password of the user.
   * @param role      The role of the user.
   * @param timestamp The timestamp for the user.
   */
  public User(String name, String email, String password, String role, LocalDateTime timestamp) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.timestamp = timestamp;
  }

  /**
   * Gets the ID of the user.
   *
   * @return The user's ID.
   */
  public Long get_id() {
    return _id;
  }

  /**
   * Sets the ID of the user.
   *
   * @param id The ID to set.
   */
  public void set_id(Long id) {
    this._id = id;
  }

  /**
   * Gets the name of the user.
   *
   * @return The user's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the user.
   *
   * @param name The name to set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the email of the user.
   *
   * @return The user's email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the user.
   *
   * @param email The email to set.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the password of the user.
   *
   * @return The user's password.
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

  /**
   * Gets the role of the user.
   *
   * @return The user's role.
   */
  public String getRole() {
    return role;
  }

  /**
   * Sets the role of the user.
   *
   * @param role The role to set.
   */
  public void setRole(String role) {
    this.role = role;
  }

  /**
   * Gets the timestamp of the user.
   *
   * @return The user's timestamp.
   */
  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp of the user.
   *
   * @param timestamp The timestamp to set.
   */
  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}