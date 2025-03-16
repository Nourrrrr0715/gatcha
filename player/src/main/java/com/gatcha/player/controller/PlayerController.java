package com.gatcha.player.controller;

import com.gatcha.player.model.Player;
import com.gatcha.player.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@SecurityRequirement(name = "Authorization")
public class PlayerController {

  public static final String DESCRIPTION = "Token of Authentication is required";
  private final PlayerService playerService;

  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @GetMapping
  @Operation(
      summary = "Get all players",
      description = "Retrieve a list of all players.",
      tags = {"Player Management"})
  public ResponseEntity<List<Player>> getAllPlayers() {
    return ResponseEntity.ok(playerService.getAllPlayers());
  }

  @GetMapping("/{id}")
  @Operation(
      summary = "Get player by ID",
      description = "Retrieve a player by their unique ID.",
      tags = {"Player Management"})
  public ResponseEntity<Player> getPlayer(@PathVariable String id) {
    return ResponseEntity.of(playerService.getPlayerById(id));
  }

  @GetMapping("/{id}/monsters")
  @Operation(
      summary = "Get player's monsters",
      description = "Retrieve a list of monsters owned by the player.",
      tags = {"Player Actions"})
  public ResponseEntity<Set<String>> getPlayerMonsters(@PathVariable String id) {
    return ResponseEntity.of(playerService.getPlayerById(id).map(Player::getMonsters));
  }

  @GetMapping("/{id}/level")
  @Operation(
      summary = "Get player's level",
      description = "Retrieve the level of the player.",
      tags = {"Player Actions"})
  public ResponseEntity<Integer> getPlayerLevel(@PathVariable String id) {
    return ResponseEntity.of(playerService.getPlayerById(id).map(Player::getLevel));
  }

  @PutMapping("/{id}/experience")
  @Operation(
      summary = "Update player's experience",
      description = "Update the experience points of a player.",
      tags = {"Player Actions"})
  public ResponseEntity<Player> updatePlayerExperience(
      @PathVariable String id, @RequestBody int experience) {
    return ResponseEntity.of(playerService.updatePlayerExperience(id, experience));
  }

  @PutMapping("/{id}/levelUp")
  @Operation(
      summary = "Level up player",
      description = "Increase the player's level by one.",
      tags = {"Player Actions"})
  public ResponseEntity<Player> levelUp(@PathVariable String id) {
    return ResponseEntity.of(playerService.levelUp(id));
  }

  @PostMapping("/{id}/monster")
  @Operation(
      summary = "Add monster to player",
      description = "Add a new monster to the player's collection.",
      tags = {"Player Actions"})
  public ResponseEntity<Player> addMonster(@PathVariable String id, @RequestBody String monsterId) {
    return ResponseEntity.of(playerService.addMonster(id, monsterId));
  }

  @DeleteMapping("{id}/monster")
  @Operation(
      summary = "Remove monster from player",
      description = "Remove a monster from the player's collection.",
      tags = {"Player Actions"})
  public ResponseEntity<Player> removeMonster(
      @PathVariable String id, @RequestBody String monsterId) {
    return ResponseEntity.of(playerService.removeMonster(id, monsterId));
  }

  @PostMapping
  @Operation(
      summary = "Create new player",
      description = "Create a new player with the provided details.",
      tags = {"Player Management"})
  public ResponseEntity<Player> createPlayer(@RequestBody @Valid Player player) {
    return ResponseEntity.ok(playerService.createPlayer(player));
  }

  @PostMapping("/byId")
  @Operation(
      summary = "Create new player by ID",
      description = "Create a new player by id",
      tags = {"Player Actions"})
  public ResponseEntity<Player> createPlayerById(@RequestBody String playerId) {
    return ResponseEntity.ok(playerService.createPlayerById(playerId));
  }

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Delete player by ID",
      description = "Delete a player by their unique ID.",
      tags = {"Player Management"})
  public ResponseEntity<String> deletePlayer(@PathVariable String id) {
    return ResponseEntity.of(playerService.deletePlayer(id));
  }

  @DeleteMapping
  @Operation(
      summary = "Delete all players",
      description = "Delete all players from the system.",
      tags = {"Player Management"})
  @Parameter(name = "Authorization", description = DESCRIPTION, in = ParameterIn.HEADER)
  public ResponseEntity<?> deleteAllPlayers() {
    playerService.deleteAllPlayers();
    return ResponseEntity.accepted().build();
  }

  @PatchMapping("/{id}")
  @Operation(
      summary = "Update player details",
      description = "Update the details of an existing player.",
      tags = {"Player Management"})
  @Parameter(name = "Authorization", description = DESCRIPTION, in = ParameterIn.HEADER)
  public ResponseEntity<Player> updatePlayer(
      @PathVariable String id, @RequestBody @Valid Player player) {
    return ResponseEntity.of(playerService.updatePlayer(id, player));
  }
}
