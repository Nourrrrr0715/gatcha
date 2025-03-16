package com.gatcha.invocation.repository;

import com.gatcha.invocation.model.Invocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvocationRepository extends MongoRepository<Invocation, String> {}
