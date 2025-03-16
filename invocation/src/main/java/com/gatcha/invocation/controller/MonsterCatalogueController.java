package com.gatcha.invocation.controller;

import com.gatcha.invocation.model.MonsterCatalogue;
import com.gatcha.invocation.service.MonsterCatalogueService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping("/MonsterCatalogue")
public class MonsterCatalogueController {

  private final MonsterCatalogueService monsterCatalogueService;

  public MonsterCatalogueController(MonsterCatalogueService monsterCatalogueService) {
    this.monsterCatalogueService = monsterCatalogueService;
  }

  @PostMapping
  public ResponseEntity<MonsterCatalogue> addMonsterToCatalogue(
      @RequestBody MonsterCatalogue monsterCatalogue) {
    return ResponseEntity.ok(this.monsterCatalogueService.addMonsterToCatalogue(monsterCatalogue));
  }

  @GetMapping
  public ResponseEntity<List<MonsterCatalogue>> getAllMonstersCatalogue() {
    return ResponseEntity.ok(this.monsterCatalogueService.getAllMonstersCatalogue());
  }
}
