package com.lastma.dtos.response;

import lombok.Data;

@Data
public class IssueTicketResponse {

    private String id;
    private String vehicleRegistrationNumber;
    private String offenceCode;
}
