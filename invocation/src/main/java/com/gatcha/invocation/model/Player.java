package com.gatcha.invocation.model;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
  private String id;
  private int level;
  private int levelUpThreshold;
  private int experience;
  private int monstersMaxSize;
  private Set<String> monsters;
}
