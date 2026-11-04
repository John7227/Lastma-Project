package com.lastma.utils;

import com.lastma.data.models.Challenge;
import com.lastma.dtos.response.SubmitChallengeResponse;

public class ChallengeMapper {

    public static SubmitChallengeResponse map(Challenge challenge) {
        SubmitChallengeResponse response = new SubmitChallengeResponse();
        response.setId(challenge.getId());
        response.setTicketId(challenge.getTicketId());
        response.setReason(challenge.getReason());
        response.setStatus(challenge.getStatus());
        response.setSubmittedAt(challenge.getSubmittedAt());
        response.setDecisionNotes(challenge.getDecisionNotes());
        response.setReviewedByOfficerId(challenge.getReviewedByOfficerId());
        response.setReviewedAt(challenge.getReviewedAt());
        return response;
    }

}
