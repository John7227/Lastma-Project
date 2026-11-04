package com.lastma.data.repositories;

import com.lastma.data.models.Challenge;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChallengeRepository extends MongoRepository<Challenge, String> {
    List<Challenge> findByTicketId(String ticketId);
}
