package com.gatcha.invocation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "monstersCatalogue")
public class MonsterCatalogue {
  @MongoId private String id;
  private Monster monster;
  private double probability;
}
