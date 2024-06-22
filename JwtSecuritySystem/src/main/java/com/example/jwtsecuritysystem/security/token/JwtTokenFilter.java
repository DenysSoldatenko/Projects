package com.example.jwtsecuritysystem.security.token;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * A filter for processing JWT tokens in incoming requests
 * and setting the authentication context.
 */
@Component
@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void doFilter(
      ServletRequest servletRequest,
      ServletResponse servletResponse,
      FilterChain filterChain
  ) throws IOException, ServletException {
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
    if (token != null && jwtTokenProvider.validateToken(token)) {
      Optional.ofNullable(jwtTokenProvider.getAuthentication(token))
          .ifPresent(getContext()::setAuthentication);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}