package com.lastma.data.repositories;

import com.lastma.data.models.Offence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OffenceRepository extends MongoRepository<Offence, String> {

    boolean existsByCode(String code);
    Optional <Offence> findByCode(String code);
}
