package com.gatcha.invocation.service;

import com.gatcha.invocation.model.Invocation;
import com.gatcha.invocation.model.Monster;
import com.gatcha.invocation.model.MonsterCatalogue;
import com.gatcha.invocation.model.Player;
import com.gatcha.invocation.repository.InvocationRepository;
import com.gatcha.invocation.repository.MonsterCatalogueRepository;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class InvocationService {

  private final MonsterCatalogueRepository monsterRepository;
  private final InvocationRepository invocationRepository;
  private final MonsterWebClientService monsterWebClientService;
  private final PlayerWebClientService playerWebClientService;

  public InvocationService(
      MonsterCatalogueRepository monsterRepository,
      InvocationRepository invocationRepository,
      MonsterWebClientService monsterWebClientService,
      PlayerWebClientService playerWebClientService) {
    this.monsterRepository = monsterRepository;
    this.invocationRepository = invocationRepository;
    this.monsterWebClientService = monsterWebClientService;
    this.playerWebClientService = playerWebClientService;
  }

  /**
   * Génère un monstre aléatoire en fonction des taux d'invocation
   *
   * @return * Le monstre généré
   */
  public MonsterCatalogue invokeMonster() {
    List<MonsterCatalogue> monsters = this.monsterRepository.findAll();
    double totalRate = monsters.stream().mapToDouble(MonsterCatalogue::getProbability).sum();
    double randomValue = new Random().nextDouble() * totalRate;

    double cumulativeRate = 0.0;
    for (MonsterCatalogue monster : monsters) {
      cumulativeRate += monster.getProbability();
      if (randomValue <= cumulativeRate) {
        return monster;
      }
    }
    return null;
  }

  // Enregistre l'invocation dans le tampon et envoie le monstre à l'API Monstre
  public Invocation processInvocation(String playerId, String authorizationHeader) {

    MonsterCatalogue generatedMonster = invokeMonster();

    if (generatedMonster == null) {
      throw new RuntimeException("Failed to generate a monster.");
    }

    Invocation invocationState = new Invocation();

    // Enregistrer le monstre dans l'API Monstre
    try {
      Monster createdMonster =
          this.monsterWebClientService.saveMonster(
              generatedMonster.getMonster(), authorizationHeader);
      // Enregistrer l'invocation dans le tampon
      invocationState.setMonsterId(createdMonster.getId());
      invocationState.setPlayerId(playerId);
      try {
        Player player = this.playerWebClientService.addMonster(createdMonster.getId(), playerId, authorizationHeader);
        invocationState.setProcessed(player.getMonsters().contains(createdMonster.getId()));
      } catch (Exception e) {
        throw new RuntimeException("Failed to attach monster to Player API.");
      }
    } catch (Exception e) {
      throw new RuntimeException("Failed to create monster in Monster API.");
    }

    return invocationRepository.save(invocationState);
  }
}
