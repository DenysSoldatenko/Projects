package com.example.jwtsecuritysystem.security.token;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Unit tests for the JwtTokenFilter class.
 */
class JwtTokenFilterTest {

  @Mock
  private JwtTokenProvider jwtTokenProvider;

  @Mock
  private HttpServletRequest request;

  @Mock
  private ServletResponse servletResponse;

  @Mock
  private FilterChain filterChain;

  private JwtTokenFilter jwtTokenFilter;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
  }

  @Test
  void testDoFilterWithInvalidToken() throws IOException, ServletException {
    String invalidToken = "invalidToken";
    when(jwtTokenProvider.resolveToken(request)).thenReturn(invalidToken);
    when(jwtTokenProvider.validateToken(invalidToken)).thenReturn(false);

    jwtTokenFilter.doFilter(request, servletResponse, filterChain);

    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }

  @Test
  void testDoFilterWithNoToken() throws IOException, ServletException {
    when(jwtTokenProvider.resolveToken(request)).thenReturn(null);

    jwtTokenFilter.doFilter(request, servletResponse, filterChain);

    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }
}
