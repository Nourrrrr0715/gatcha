package com.gatcha.player.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "players")
public class Player {
  @MongoId @NotNull private String id;

  @NotNull
  @Min(0)
  @Max(50)
  private int level = 0;

  private int levelUpThreshold = 50;

  @Min(0)
  @NotNull
  private int experience = 0;

  private int monstersMaxSize = 10;
  @NotNull private Set<String> monsters = new HashSet<>();

  public void addExperience(int experiencePoints) {
    this.experience += experiencePoints;
    if (this.experience >= this.levelUpThreshold) {
      this.levelUp();
    }
  }

  public void levelUp() {
    if(level == 50) {
      return;
    }
    this.level++;
    this.monstersMaxSize++;
    this.experience = 0;
    // calculate new threshold
    if (this.level >= 2) {
      this.levelUpThreshold = (int) (levelUpThreshold * 1.1);
    }
  }

  public void addMonster(String monsterId) {
    if (this.monsters.size() < this.monstersMaxSize) {
      this.monsters.add(monsterId);
    }
  }
}
