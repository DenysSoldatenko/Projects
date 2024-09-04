package com.example.springsecuritysystem.security;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Unit tests for the JwtAuthFilter class.
 */
@ExtendWith(MockitoExtension.class)
class JwtAuthFilterTest {

  private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9"
      + "yaXR5IjoiUk9MRV9VU0VSIn1d"
      + "LCJzdWIiOiJqYW5lLmRvZUBnbWFpbC5jb20iLCJpYXQiOjE2OTY5MjczODQsImV4cCI6MTY5Njk2MzM4NH0.PMd"
      + "J7yZwMfXGX39QGJ9trKlywaWBDJHin0q2SPsXbck";
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private FilterChain filterChain;

  @Mock
  private JwtService jwtService;

  @InjectMocks
  private JwtAuthFilter jwtAuthFilter;

  /**
   * Set up the necessary components for testing a filter.
   */
  @BeforeEach
  public void setUp() {
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    filterChain = mock(FilterChain.class);
  }

  @Test
  void testDoFilterInternalWithValidToken() throws IOException, ServletException {
    lenient().when(jwtService.extractUsername(token)).thenReturn("jane.doe@gmail.com");

    jwtAuthFilter.doFilterInternal(request, response, filterChain);

    // Verify that doFilter was called with the expected parameters and only once
    verify(filterChain, times(1)).doFilter(request, response);
  }

  @Test
  void testDoFilterInternalWithMissingAuthorizationHeader()
      throws IOException, ServletException {
    MockHttpServletRequest requestWithoutAuthHeader = new MockHttpServletRequest();

    jwtAuthFilter.doFilterInternal(requestWithoutAuthHeader, response, filterChain);

    verify(filterChain, times(1)).doFilter(requestWithoutAuthHeader, response);
    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }

  @Test
  void testDoFilterInternalWithInvalidToken() throws IOException, ServletException {
    lenient().when(jwtService.extractUsername(token)).thenReturn("invaliduser@gmail.com");

    jwtAuthFilter.doFilterInternal(request, response, filterChain);

    verify(filterChain, times(1)).doFilter(request, response);
    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }
}
