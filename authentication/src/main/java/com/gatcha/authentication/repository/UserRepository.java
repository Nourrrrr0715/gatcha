package com.gatcha.authentication.repository;

import com.gatcha.authentication.domain.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUserId(String userId);
}
