package com.lastma.dtos.requests;

import lombok.Data;

@Data
public class IssueTicketRequest {

    private String vehicleRegistrationNumber;
    private String offenceCode;
}
