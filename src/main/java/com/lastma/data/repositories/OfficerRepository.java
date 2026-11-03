package com.lastma.data.repositories;

import com.lastma.data.models.Officer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OfficerRepository extends MongoRepository<Officer, String> {
    boolean existsByEmail(String email);
    Officer findByEmail(String email);
}
