package tech.eproducts.user_management_service.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import tech.eproducts.user_management_service.model.User;

/**
 * This class represents a user principal in the application.
 * It implements the UserDetails interface from Spring Security.
 */
public class UserPrincipal implements UserDetails {
  private Long _id;
  private String name;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  /**
   * Constructs a new UserPrincipal.
   *
   * @param _id         The user's ID
   * @param name        The user's name
   * @param email       The user's email
   * @param password    The user's password
   * @param authorities The user's granted authorities
   */
  public UserPrincipal(Long _id, String name, String email, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this._id = _id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  /**
   * Creates a UserPrincipal from a User object.
   *
   * @param user The User object
   * @return A new UserPrincipal instance
   */
  public static UserPrincipal create(User user) {
    List<GrantedAuthority> authorities = Collections
        .singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));

    return new UserPrincipal(
        user.get_id(),
        user.getName(),
        user.getEmail(),
        user.getPassword(),
        authorities);
  }

  /**
   * Gets the user's ID.
   *
   * @return The user's ID
   */
  public Long getId() {
    return _id;
  }

  /**
   * Gets the user's name.
   *
   * @return The user's name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the user's username (email in this case).
   *
   * @return The user's email
   */
  @Override
  public String getUsername() {
    return email;
  }

  /**
   * Gets the user's password.
   *
   * @return The user's password
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Gets the user's granted authorities.
   *
   * @return The user's authorities
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  /**
   * Checks if the user's account is non-expired.
   *
   * @return true (account is always considered non-expired)
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Checks if the user's account is non-locked.
   *
   * @return true (account is always considered non-locked)
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Checks if the user's credentials are non-expired.
   *
   * @return true (credentials are always considered non-expired)
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Checks if the user's account is enabled.
   *
   * @return true (account is always considered enabled)
   */
  @Override
  public boolean isEnabled() {
    return true;
  }

  /**
   * Checks if this UserPrincipal is equal to another object.
   *
   * @param o The object to compare with
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserPrincipal that = (UserPrincipal) o;
    return Objects.equals(_id, that._id);
  }

  /**
   * Generates a hash code for this UserPrincipal.
   *
   * @return The hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(_id);
  }
}
