package com.gatcha.invocation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "invocations")
public class Invocation {
  @MongoId private String id;
  private String monsterId; // ID du monstre généré
  private String playerId; // ID du joueur qui invoque le monstre
  private boolean processed; // Indique si l'invocation a été traitée avec succès
}
