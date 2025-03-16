package com.gatcha.player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gatcha.player.model.Player;
import com.gatcha.player.repository.PlayerRepository;
import com.gatcha.player.service.PlayerService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PlayerServiceTest {

  // mock pour la classe PlayerRepository
  @Mock private PlayerRepository playerRepository;

  // injection automatique des mocks dans le service
  @InjectMocks private PlayerService playerService;

  @BeforeEach
  void setUp() {
    // Initialisation des mocks
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreatePlayer() {
    Player player = new Player();
    when(playerRepository.save(player)).thenReturn(player);

    Player createdPlayer = playerService.createPlayer(player);

    assertNotNull(createdPlayer);
    verify(playerRepository, times(1)).save(player);
  }

  @Test
  void testGetPlayerById() {
    String playerId = "123";
    Player player = new Player();
    when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

    Optional<Player> foundPlayer = playerService.getPlayerById(playerId);

    assertTrue(foundPlayer.isPresent());
    assertEquals(player, foundPlayer.get());
  }

  @Test
  void testGetAllPlayers() {
    List<Player> players = List.of(new Player(), new Player());
    when(playerRepository.findAll()).thenReturn(players);

    List<Player> allPlayers = playerService.getAllPlayers();

    assertEquals(2, allPlayers.size());
    verify(playerRepository, times(1)).findAll();
  }

  @Test
  void testDeletePlayer() {
    String playerId = "123";
    Player player = new Player();
    player.setId(playerId);
    when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

    String deletedPlayerId = playerService.deletePlayer(playerId).get();

    assertEquals(playerId, deletedPlayerId);
    verify(playerRepository, times(1)).delete(player);
  }

  @Test
  void testDeletePlayerNotFound() {
    String playerId = "123";
    when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

    Optional<String> deletedPlayerId = playerService.deletePlayer(playerId);

    assertEquals(deletedPlayerId, Optional.empty());
  }
}
