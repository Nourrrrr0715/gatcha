package com.gatcha.invocation.filter;

import com.gatcha.invocation.service.AuthenticationWebClientService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFilter implements Filter {

  private final AuthenticationWebClientService authenticationWebClientService;

  public AuthenticationFilter(AuthenticationWebClientService authenticationWebClientService) {
    this.authenticationWebClientService = authenticationWebClientService;
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
    String uri = httpRequest.getRequestURI();

    if (uri.contains("/swagger-ui/") || uri.contains("/v3/api-docs")) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    String authorization = httpRequest.getHeader("Authorization");

    if (authorization == null || !authorization.startsWith("Bearer")) {
      httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
    // check if token is valid
    else {
      try {
        Boolean isAuthenticated =
            authenticationWebClientService.authenticate(authorization.split(" ")[1]);
        if (!isAuthenticated) {
          httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
      } catch (Exception e) {
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      }
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
