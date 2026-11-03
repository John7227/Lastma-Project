package com.lastma.services;

import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.response.IssueTicketResponse;
import org.springframework.stereotype.Service;

@Service
public interface TicketService {

    IssueTicketResponse issueTicket(IssueTicketRequest request);
    IssueTicketResponse findTicketById(String id);
}
