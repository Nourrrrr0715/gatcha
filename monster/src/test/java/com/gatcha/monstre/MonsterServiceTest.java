package com.gatcha.monstre;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gatcha.monster.model.Monster;
import com.gatcha.monster.repository.MonsterRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.gatcha.monster.service.MonsterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MonsterServiceTest {

  @Mock
  private MonsterRepository monsterRepository;

  @InjectMocks
  private MonsterService monsterService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllMonsters() {
    Monster monster1 = new Monster();
    Monster monster2 = new Monster();
    when(monsterRepository.findAll()).thenReturn(Arrays.asList(monster1, monster2));

    List<Monster> monsters = monsterService.getAllMonsters();

    assertEquals(2, monsters.size());
    verify(monsterRepository, times(1)).findAll();
  }

  @Test
  void testAjouterMonster() {
    Monster monster = new Monster();
    when(monsterRepository.save(monster)).thenReturn(monster);

    Monster savedMonster = monsterService.ajouterMonster(monster);

    assertNotNull(savedMonster);
    verify(monsterRepository, times(1)).save(monster);
  }

  @Test
  void testDeleteMonster() {
    Monster monster = new Monster();
    monster.setId("1");
    when(monsterRepository.findById("1")).thenReturn(Optional.of(monster));

    Optional<String> deletedId = monsterService.deleteMonster("1");

    assertTrue(deletedId.isPresent());
    assertEquals("1", deletedId.get());
    verify(monsterRepository, times(1)).delete(monster);
  }

  @Test
  void testGetMonsterById() {
    Monster monster = new Monster();
    when(monsterRepository.findById("1")).thenReturn(Optional.of(monster));

    Optional<Monster> foundMonster = monsterService.getMonsterById("1");

    assertTrue(foundMonster.isPresent());
    verify(monsterRepository, times(1)).findById("1");
  }

  @Test
  void testUpdateMonster() {
    Monster existingMonster = new Monster();
    existingMonster.setId("1");
    Monster updatedMonster = new Monster();
    updatedMonster.setName("Updated Name");

    when(monsterRepository.findById("1")).thenReturn(Optional.of(existingMonster));
    when(monsterRepository.save(existingMonster)).thenReturn(existingMonster);

    Optional<Monster> result = monsterService.updateMonster("1", updatedMonster);

    assertTrue(result.isPresent());
    assertEquals("Updated Name", result.get().getName());
    verify(monsterRepository, times(1)).save(existingMonster);
  }
}