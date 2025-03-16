package com.gatcha.invocation.repository;

import com.gatcha.invocation.model.MonsterCatalogue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterCatalogueRepository extends MongoRepository<MonsterCatalogue, String> {}
