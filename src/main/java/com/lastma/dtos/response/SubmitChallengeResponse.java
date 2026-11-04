package com.lastma.dtos.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SubmitChallengeResponse {

    private String id;
    private String ticketId;
    private String reason;
    private String status;
    private String decisionNotes;
    private String reviewedByOfficerId;
    private LocalDate submittedAt;
    private LocalDate reviewedAt;
}
