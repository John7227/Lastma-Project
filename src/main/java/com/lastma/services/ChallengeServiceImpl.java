package com.lastma.services;

import com.lastma.data.models.Challenge;
import com.lastma.data.repositories.ChallengeRepository;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.dtos.requests.SubmitChallengeRequest;
import com.lastma.dtos.response.SubmitChallengeResponse;
import com.lastma.exceptions.ChallengeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.lastma.utils.ChallengeMapper.map;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Override
    public SubmitChallengeResponse submitChallenge(SubmitChallengeRequest request) {
        if(request.getReason() == null || request.getReason().isBlank()) {
            throw new ChallengeException("Reason is required");
        }
        if(ticketRepository.findById(request.getTicketId()).isEmpty()) {
            throw new ChallengeException("Ticket is not found");
        }

        Challenge challenge = new Challenge();
        challenge.setTicketId(request.getTicketId());
        challenge.setReason(request.getReason());
        challenge.setStatus("PENDING");
        challenge.setSubmittedAt(LocalDate.now());

        Challenge savedChallenge = challengeRepository.save(challenge);
        return map(savedChallenge);
    }

    @Override
    public SubmitChallengeResponse findChallengeById(String id) {
        Optional<Challenge> result = challengeRepository.findById(id);
        if(result.isEmpty()) {
            throw new ChallengeException("Challenge not found");
        }
        Challenge challenge = result.get();

        return map(challenge);
    }

    @Override
    public List<SubmitChallengeResponse> findChallengesByTicketId(String ticketId) {
        List<Challenge> challenges = challengeRepository.findByTicketId(ticketId);
        List<SubmitChallengeResponse> responses = new ArrayList<>();

        for(Challenge challenge: challenges) {
            responses.add(map(challenge));
        }
        return responses;
    }

    @Override
    public List<SubmitChallengeResponse> findAllChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();
        List<SubmitChallengeResponse> responses = new ArrayList<>();

        for(Challenge challenge: challenges) {
            responses.add(map(challenge));
        }
        return responses;
    }

}
