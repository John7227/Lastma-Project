package com.lastma.data.repositories;

import com.lastma.data.models.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface DriverRepository extends MongoRepository<Driver, String> {
    boolean existsByEmail(String email);
    Optional<Driver> findByEmail(String email);
}
