package com.lastma.dtos.requests;

import lombok.Data;

@Data
public class UpdateChallengeStatusRequest {

    private String status;
    private String decisionNotes;
    private String reviewedByOfficerId;
}
