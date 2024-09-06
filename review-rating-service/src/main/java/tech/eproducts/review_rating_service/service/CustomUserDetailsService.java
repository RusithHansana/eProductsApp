package tech.eproducts.review_rating_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.eproducts.review_rating_service.model.User;
import tech.eproducts.review_rating_service.repository.UserRepository;
import tech.eproducts.review_rating_service.security.UserPrincipal;

/**
 * Custom implementation of UserDetailsService for handling user authentication.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  /**
   * Loads a user by their email address.
   *
   * @param email The email address of the user to load.
   * @return UserDetails object containing the user's information.
   * @throws UsernameNotFoundException if the user is not found.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User not found with email: " + email);
    }
    return UserPrincipal.create(user);
  }

  /**
   * Loads a user by their ID.
   *
   * @param id The ID of the user to load.
   * @return UserDetails object containing the user's information.
   * @throws UsernameNotFoundException if the user is not found.
   */
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(
        () -> new UsernameNotFoundException("User not found with id : " + id));

    return UserPrincipal.create(user);
  }
}
