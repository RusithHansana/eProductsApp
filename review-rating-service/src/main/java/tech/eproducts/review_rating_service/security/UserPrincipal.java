/**
 * Represents a user principal for Spring Security authentication.
 * This class implements UserDetails and provides user information and authorities.
 */
package tech.eproducts.review_rating_service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.eproducts.review_rating_service.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserPrincipal implements UserDetails {
  private Long _id;
  private String name;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long _id, String name, String email, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this._id = _id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  /**
   * Creates a UserPrincipal instance from a User object.
   *
   * @param user The User object to create the UserPrincipal from
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

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserPrincipal that = (UserPrincipal) o;
    return Objects.equals(_id, that._id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_id);
  }
}
