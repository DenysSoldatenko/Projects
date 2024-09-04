package com.example.springsecuritysystem.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import com.example.springsecuritysystem.dao.UserDao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter for authenticating requests using JWT (JSON Web Tokens).
 */
@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private final UserDao userDao;
  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
  ) throws ServletException, IOException {
    String authHeader = request.getHeader(AUTHORIZATION);
    String userEmail;
    String jwtToken;

    if (authHeader == null || !authHeader.startsWith("Bearer")) {
      filterChain.doFilter(request, response);
      return;
    }

    jwtToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwtToken);

    if (userEmail != null && getContext().getAuthentication() == null) {
      UserDetails userDetails = userDao.findUserByEmail(userEmail);
      if (jwtService.validateToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken
            = new UsernamePasswordAuthenticationToken(
               userDetails, null, userDetails.getAuthorities()
          );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        getContext().setAuthentication(authenticationToken);
      }
    }
  }
}
