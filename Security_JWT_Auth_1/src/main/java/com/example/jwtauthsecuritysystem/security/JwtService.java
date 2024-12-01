package com.example.jwtauthsecuritysystem.security;

import static io.jsonwebtoken.io.Decoders.BASE64;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.lang.System.currentTimeMillis;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Service for handling JWT (JSON Web Token) operations,
 * such as token generation, extraction, and validation.
 */
@Service
public class JwtService {

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.token-expiration-duration-ms}")
  private long tokenExpirationDurationMs;

  /**
   * Generates a JWT token for the provided UserDetails, with no extra claims.
   *
   * @param userDetails The UserDetails object representing the authenticated user.
   * @return The generated JWT token.
   */
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  /**
   * Generates a JWT token for the provided UserDetails and additional claims.
   *
   * @param extraClaims   Additional claims to include in the JWT payload.
   * @param userDetails   The UserDetails representing the user.
   * @return The generated JWT token.
   */
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts.builder()
    .claims(extraClaims)
    .subject(userDetails.getUsername())
    .issuedAt(new Date(currentTimeMillis()))
    .expiration(new Date(currentTimeMillis() + tokenExpirationDurationMs))
    .signWith(getSignInKey())
    .compact();
  }

  /**
   * Extracts a specific claim from the JWT token using the provided claims' resolver.
   *
   * @param token          The JWT token from which to extract the claim.
   * @param claimsResolver A function that extracts the desired claim from the token.
   * @param <T>            The type of the claim.
   * @return The extracted claim.
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  /**
   * Checks if the JWT token is valid for the provided user.
   *
   * @param token        The JWT token to validate.
   * @param userDetails  The UserDetails object to match against the token's subject.
   * @return {@code true} if the token is valid, {@code false} otherwise.
   */
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  /**
   * Extracts the username (subject) from the JWT token.
   *
   * @param token The JWT token from which to extract the username.
   * @return The username (subject) of the token.
   */
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
    .verifyWith(getSignInKey())
    .build()
    .parseSignedClaims(token)
    .getPayload();
  }

  private SecretKey getSignInKey() {
    byte[] keyBytes = BASE64.decode(secretKey);
    return hmacShaKeyFor(keyBytes);
  }
}