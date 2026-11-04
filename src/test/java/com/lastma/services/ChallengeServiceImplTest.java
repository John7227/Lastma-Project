package com.lastma.services;

import com.lastma.data.models.Ticket;
import com.lastma.data.repositories.ChallengeRepository;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.dtos.requests.SubmitChallengeRequest;
import com.lastma.dtos.response.SubmitChallengeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChallengeServiceImplTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Test
    public void testSubmitChallenge_SavesChallengeForExistingTicket() {
        assertEquals(0, challengeRepository.count());
        Ticket ticket = new Ticket();
        ticket.setVehicleRegistrationNumber("XYZ-123XY");
        ticket.setOffenceCode("JOH");
        Ticket savedTicket = ticketRepository.save(ticket);

        SubmitChallengeRequest request = new SubmitChallengeRequest();
        request.setTicketId(savedTicket.getId());
        request.setReason("I was not at this location");

        SubmitChallengeResponse response = new SubmitChallengeResponse();
        assertNotNull(response.getId());
        assertEquals(savedTicket.getId(), response.getTicketId());
        assertEquals("I was not as this location", response.getReason());
        assertEquals("PENDING", response.getStatus());
        assertEquals(1, challengeRepository.count());
    }

}