package com.gatcha.invocation.service;

import com.gatcha.invocation.model.Monster;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class MonsterWebClientService {
  private static final String SAVE_MONSTER_URI_TEMPLATE = "http://%s/monsters";
  public static final String AUTHORIZATION = "Authorization";

  @Value("${application.monsterUrl}")
  private String monsterUrl;

  private final RestTemplate restTemplate;

  public MonsterWebClientService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public Monster saveMonster(Monster monster, String authorizationHeader) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set(AUTHORIZATION, authorizationHeader);
    monster.setId(UUID.randomUUID().toString()); // Génère un ID unique pour le monstre
    HttpEntity<Monster> request = new HttpEntity<>(monster, headers);
    return this.restTemplate.postForObject(
        SAVE_MONSTER_URI_TEMPLATE.formatted(monsterUrl), request, Monster.class);
  }
}
