package com.lastma.data.repositories;

import com.lastma.data.models.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    boolean existsByRegistrationNumber(String registrationNumber);
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
}
