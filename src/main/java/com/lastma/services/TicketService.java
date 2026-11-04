package com.lastma.services;

import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.response.IssueTicketResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    IssueTicketResponse issueTicket(IssueTicketRequest request);
    IssueTicketResponse findTicketById(String id);
    List<IssueTicketResponse> findTicketsByVehicleRegistrationNumber(String registrationNumber);
    List<IssueTicketResponse> findAllTickets();
}
