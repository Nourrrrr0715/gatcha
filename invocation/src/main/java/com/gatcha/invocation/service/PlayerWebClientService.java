package com.gatcha.invocation.service;

import com.gatcha.invocation.model.Player;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerWebClientService {
  private static final String SAVE_PLAYER_URI_TEMPLATE = "http://%s/players/%s/monster";
  public static final String AUTHORIZATION = "AUTHORIZATION";

  @Value("${application.playerUrl}")
  private String playerUrl;

  private final RestTemplate restTemplate;

  public PlayerWebClientService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public Player addMonster(String monsterId, String playerId, String authorizationHeader) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set(AUTHORIZATION, authorizationHeader);
    HttpEntity<String> request = new HttpEntity<>(monsterId, headers);
    return this.restTemplate.postForObject(
        SAVE_PLAYER_URI_TEMPLATE.formatted(playerUrl, playerId), request, Player.class);
  }
}
