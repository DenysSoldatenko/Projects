package com.example.springsecuritysystem.security;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.lang.System.currentTimeMillis;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Utility class for working with JSON Web Tokens (JWTs).
 */
@Service
public class JwtService {

  private final String secretKey = "eyJhbGciOiJIUzI1NiJ9eyJSb2xlIjoiQWRtaW"
      + "4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTY5NjI"
      + "0Mjg1NywiaWF0IjoxNjk2MjQyODU3fQ";

  /**
   * Extracts the username (subject) from the provided JWT token.
   *
   * @param token The JWT token from which the username (subject) will be extracted.
   * @return The username (subject) stored in the token.
   */
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  /**
   * Extracts the expiration date from the provided JWT token.
   *
   * @param token The JWT token from which the expiration date will be extracted.
   * @return The expiration date of the token.
   */
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  /**
   * Extracts a specific claim from the provided JWT token.
   *
   * @param token The JWT token from which the claim will be extracted.
   * @param claimsResolver A function that resolves the specific claim from the Claims object.
   * @param <T> The type of the claim to be extracted (for example, String, Date, etc.).
   * @return The extracted claim of the specified type.
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
    .setSigningKey(secretKey)
    .parseClaimsJws(token)
    .getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  /**
   * Generates a JWT token for a user with the specified claims.
   *
   * @param userDetails The user details.
   * @return The generated JWT token.
   */
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", userDetails.getAuthorities());
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject) {

    return Jwts.builder()
    .setClaims(claims)
    .setSubject(subject)
    .setIssuedAt(new Date(currentTimeMillis()))
    .setExpiration(new Date(currentTimeMillis() + 1000 * 60 * 60 * 10))
    .signWith(HS256, secretKey)
    .compact();
  }

  /**
   * Validates the provided JWT token by checking if it matches the username
   * and if the token is not expired.
   *
   * @param token The JWT token to be validated.
   * @param userDetails The UserDetails object that contains the username to match against.
   * @return {@code true} if the token is valid, {@code false} otherwise.
   */
  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}