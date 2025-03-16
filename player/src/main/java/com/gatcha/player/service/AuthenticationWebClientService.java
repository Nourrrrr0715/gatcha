package com.gatcha.player.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationWebClientService {

  private static final String AUTHENTICATION_URI_TEMPLATE = "http://%s/login";

  @Value("${application.authenticationUrl}")
  private String authenticationUrl;

  private final RestTemplate restTemplate;

  public AuthenticationWebClientService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public Boolean authenticate(String token) {
    return this.restTemplate.postForObject(
        AUTHENTICATION_URI_TEMPLATE.formatted(authenticationUrl), token, Boolean.class);
  }
}
