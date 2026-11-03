package com.lastma.data.repositories;

import com.lastma.data.models.Officer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OfficerRepository extends MongoRepository<Officer, String> {
    boolean existsByEmail(String email);
    Optional<Officer> findByEmail(String email);
}
