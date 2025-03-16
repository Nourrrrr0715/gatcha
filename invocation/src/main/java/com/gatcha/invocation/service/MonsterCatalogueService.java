package com.gatcha.invocation.service;

import com.gatcha.invocation.model.MonsterCatalogue;
import com.gatcha.invocation.repository.MonsterCatalogueRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MonsterCatalogueService {
  private final MonsterCatalogueRepository monsterRepository;

  public MonsterCatalogueService(MonsterCatalogueRepository monsterRepository) {
    this.monsterRepository = monsterRepository;
  }

  public MonsterCatalogue addMonsterToCatalogue(MonsterCatalogue monsterCatalogue) {
    return this.monsterRepository.save(monsterCatalogue);
  }

  public List<MonsterCatalogue> getAllMonstersCatalogue() {
    return this.monsterRepository.findAll();
  }
}
