package com.example.jwtsecuritysystem.security.token;

import com.example.jwtsecuritysystem.models.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Provides utility methods for working with JWT tokens.
 */
@Data
@Component
public class JwtTokenProvider {

  private final UserDetailsService userDetailsService;

  @Value("${jwt.token.secret}")
  private String secret;

  @Value("${jwt.token.expired}")
  private long validityInMilliseconds;

  /**
   * Constructs a JwtTokenProvider instance.
   *
   * @param userDetailsService The service for loading user details based on username.
   */
  @Autowired
  public JwtTokenProvider(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  /**
   * Initializes the JWT provider by encoding the secret key in Base64.
   * This method is called after the bean is initialized.
   */
  @PostConstruct
  protected void init() {
    secret = Base64.getEncoder().encodeToString(secret.getBytes());
  }

  /**
   * Creates a JWT token for the given username and roles.
   *
   * @param username The username for which the token is created.
   * @param roles    The roles associated with the user.
   * @return A JWT token as a String.
   */
  public String createToken(String username, List<Role> roles) {

    Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", getRoleNames(roles));

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
    .setClaims(claims)
    .setIssuedAt(now)
    .setExpiration(validity)
    .signWith(SignatureAlgorithm.HS256, secret)
    .compact();
  }

  /**
   * Extracts the authentication object from the provided JWT token.
   *
   * @param token The JWT token from which the authentication is extracted.
   * @return The authentication object representing the user's credentials.
   */
  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  /**
   * Retrieves the username from a JWT token.
   *
   * @param token The JWT token as a String.
   * @return The username extracted from the token.
   */
  public String getUsername(String token) {
    return Jwts.parser()
    .setSigningKey(secret)
    .parseClaimsJws(token)
    .getBody()
    .getSubject();
  }

  /**
   * Resolves a JWT token from the request.
   *
   * @param req The HttpServletRequest object.
   * @return The JWT token as a String, or null if not found.
   */
  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  /**
   * Validates a JWT token by checking its expiration and verifying its integrity.
   *
   * @param token The JWT token to validate.
   * @return {@code true} if the token is valid (not expired), {@code false} otherwise.
   * @throws JwtException If the token is invalid or cannot be parsed.
   * @throws IllegalArgumentException If the token is not properly formatted.
   */
  @SneakyThrows({JwtException.class, IllegalArgumentException.class})
  public boolean validateToken(String token) {
    Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    return !claims.getBody().getExpiration().before(new Date());
  }

  private List<String> getRoleNames(List<Role> userRoles) {
    return userRoles.stream().map(Role::getName).toList();
  }
}