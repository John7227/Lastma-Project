package com.lastma.utils;

import com.lastma.data.models.Ticket;
import com.lastma.dtos.response.IssueTicketResponse;

public class TicketMapper {

    public static IssueTicketResponse map(Ticket ticket) {
        IssueTicketResponse response = new IssueTicketResponse();
        response.setId(ticket.getId());
        response.setOffenceCode(ticket.getOffenceCode());
        response.setVehicleRegistrationNumber(ticket.getVehicleRegistrationNumber());
        return response;
    }
}
