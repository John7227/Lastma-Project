package com.lastma.services;

import com.lastma.dtos.requests.SubmitChallengeRequest;
import com.lastma.dtos.response.SubmitChallengeResponse;
import org.springframework.stereotype.Service;

@Service
public interface ChallengeService {
    SubmitChallengeResponse submitChallenge(SubmitChallengeRequest request);
}
