package com.lastma.services;

import com.lastma.dtos.requests.SubmitChallengeRequest;
import com.lastma.dtos.response.SubmitChallengeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChallengeService {
    SubmitChallengeResponse submitChallenge(SubmitChallengeRequest request);
    SubmitChallengeResponse findChallengeById(String id);
    List<SubmitChallengeResponse> findChallengesByTicketId(String ticketId);
    List<SubmitChallengeResponse> findAllChallenges();
}
