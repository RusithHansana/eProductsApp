package tech.eproducts.user_management_service.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import jakarta.servlet.http.Cookie;
import tech.eproducts.user_management_service.model.User;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * This class provides JWT (JSON Web Token) related functionality for user authentication and authorization.
 */
@Component
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  @Value("${app.jwtSecret}")
  private String jwtSecret;

  @Value("${app.jwtExpirationInMs}")
  private int jwtExpirationInMs;

  private SecretKey key;

  /**
   * Initializes the JWT secret key.
   */
  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Generates a JWT token for the given user.
   *
   * @param user The user for whom the token is generated
   * @return The generated JWT token
   */
  public String generateToken(User user) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

    return Jwts.builder()
        .setSubject(Long.toString(user.get_id()))
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(key)
        .compact();
  }

  /**
   * Extracts the user ID from the given JWT token.
   *
   * @param token The JWT token
   * @return The user ID extracted from the token
   */
  public Long getUserIdFromJWT(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();

    return Long.parseLong(claims.getSubject());
  }

  /**
   * Validates the given JWT token.
   *
   * @param authToken The JWT token to validate
   * @return true if the token is valid, false otherwise
   */
  public boolean validateToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
      return true;

    } catch (SecurityException ex) {
      logger.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
    }
    return false;
  }

  /**
   * Creates a cookie containing the JWT token.
   *
   * @param token The JWT token to be stored in the cookie
   * @return The created cookie
   */
  public Cookie createJwtCookie(String token) {
    Cookie cookie = new Cookie("jwt", token);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setMaxAge((int) (jwtExpirationInMs / 1000));
    cookie.setPath("/");
    return cookie;
  }
}
