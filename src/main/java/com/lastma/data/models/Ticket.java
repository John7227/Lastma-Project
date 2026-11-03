package com.lastma.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Ticket {

    @Id
    private String id;
    private String offenceId;
    private String vehicleId;
    private String issuedByOfficerId;
    private String ticketNumber;
    private String Location;
    private String description;
    private String status;
    private int violationFee;
    private LocalDate issuedDate;
    private LocalDate dueDate;
    private String offenceCode;
    private String vehicleRegistrationNumber;
}
