package com.gatcha.invocation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
  private String skillName;
  private int baseDamage;
  private double supplementHitChance;
  private int coolDown;
  private int level;
  private int maxLevel;
}
