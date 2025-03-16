package com.gatcha.monster.controller;

import com.gatcha.monster.model.Monster;
import com.gatcha.monster.service.MonsterService;
import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping("/monsters")
public class MonsterController {

  private final MonsterService monsterService;

  public MonsterController(MonsterService monsterService) {
    this.monsterService = monsterService;
  }

  @GetMapping
  public ResponseEntity<List<Monster>> getAllMonsters() {
    return ResponseEntity.ok(monsterService.getAllMonsters());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Monster> getMonsterById(@PathVariable String id) {
    return ResponseEntity.of(monsterService.getMonsterById(id));
  }

  @PostMapping
  public ResponseEntity<Monster> addMonster(@RequestBody Monster monster) {
    return ResponseEntity.ok(monsterService.ajouterMonster(monster));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Monster> updateMonster(@PathVariable String id, @RequestBody Monster monster) {
    return ResponseEntity.of(monsterService.updateMonster(id, monster));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteMonster(@PathVariable String id) {
    return ResponseEntity.of(monsterService.deleteMonster(id));
  }
}
