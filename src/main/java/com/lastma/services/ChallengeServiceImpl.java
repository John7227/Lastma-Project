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

}
