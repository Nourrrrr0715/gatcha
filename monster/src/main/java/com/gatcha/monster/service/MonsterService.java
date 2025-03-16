package com.gatcha.monster.service;

import com.gatcha.monster.model.Monster;
import com.gatcha.monster.repository.MonsterRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MonsterService {

  private final MonsterRepository monsterRepository;

  public MonsterService(MonsterRepository monsterRepository) {
    this.monsterRepository = monsterRepository;
  }

  public List<Monster> getAllMonsters() {
    return monsterRepository.findAll();
  }

  public Monster ajouterMonster(Monster monster) {
    return monsterRepository.save(monster);
  }

  public Optional<String> deleteMonster(String id) {
    return getMonsterById(id)
            .map(monster -> {
              monsterRepository.delete(monster);
              return monster.getId();
            });
  }

  public Optional<Monster> getMonsterById(String id) {
    return monsterRepository.findById(id);
  }

  public Optional<Monster> updateMonster(String id, Monster monster) {
    return getMonsterById(id)
            .map(toUpdateMonster -> {
              toUpdateMonster.setName(monster.getName());
              toUpdateMonster.setMonsterType(monster.getMonsterType());
              toUpdateMonster.setHp(monster.getHp());
              toUpdateMonster.setAtk(monster.getAtk());
              toUpdateMonster.setDef(monster.getDef());
              toUpdateMonster.setVit(monster.getVit());
              toUpdateMonster.setLevel(monster.getLevel());
              toUpdateMonster.setExperience(monster.getExperience());
              toUpdateMonster.setSkills(monster.getSkills());
              return monsterRepository.save(toUpdateMonster);
            });
  }
}
