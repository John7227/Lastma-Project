package com.lastma.data.repositories;

import com.lastma.data.models.Challenge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChallengeRepository extends MongoRepository<Challenge, String> {
}
