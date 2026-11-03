package com.lastma.data.repositories;

import com.lastma.data.models.Offence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OffenceRepository extends MongoRepository<Offence, String> {

    boolean existsByCode(String code);
}
