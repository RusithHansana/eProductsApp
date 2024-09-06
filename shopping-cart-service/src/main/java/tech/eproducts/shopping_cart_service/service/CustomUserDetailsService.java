package tech.eproducts.shopping_cart_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.eproducts.shopping_cart_service.model.User;
import tech.eproducts.shopping_cart_service.repository.UserRepository;
import tech.eproducts.shopping_cart_service.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User not found with email: " + email);
    }
    return UserPrincipal.create(user);
  }

  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(
        () -> new UsernameNotFoundException("User not found with id : " + id));

    return UserPrincipal.create(user);
  }
}
