package com.gatcha.invocation.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // Génère un constructeur avec tous les attributs
@NoArgsConstructor // Génère un constructeur vide
public class Monster {

  private String id;
  private String name;
  private MonsterType monsterType; // Ex: Feu, Eau, Vent

  private int hp; // Points de vie
  private int atk; // Attaque
  private int def; // Défense
  private int vit; // Vitesse

  private int level = 0;
  private int experience = 0;

  private List<Skill> skills = new ArrayList<>();
}
