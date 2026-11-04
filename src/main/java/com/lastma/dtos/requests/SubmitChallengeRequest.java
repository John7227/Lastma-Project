package com.lastma.dtos.requests;

import lombok.Data;

@Data
public class SubmitChallengeRequest {

    private String ticketId;
    private String reason;

}
