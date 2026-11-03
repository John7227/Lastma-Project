package com.lastma.data.repositories;

import com.lastma.data.models.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DriverRepository extends MongoRepository<Driver, String> {
    boolean existsByEmail(String email);
    Driver findByEmail(String email);
}
