package com.lastma.services;

import com.lastma.data.models.Challenge;
import com.lastma.data.models.Ticket;
import com.lastma.data.repositories.ChallengeRepository;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.dtos.requests.SubmitChallengeRequest;
import com.lastma.dtos.response.SubmitChallengeResponse;
import com.lastma.exceptions.ChallengeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChallengeServiceImplTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeService challengeService;

    @BeforeEach
    public void setUp() {
        challengeRepository.deleteAll();
        ticketRepository.deleteAll();
    }
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

        SubmitChallengeResponse response = challengeService.submitChallenge(request);
        assertNotNull(response.getId());
        assertEquals(savedTicket.getId(), response.getTicketId());
        assertEquals("I was not at this location", response.getReason());
        assertEquals("PENDING", response.getStatus());
        assertEquals(1, challengeRepository.count());
    }

    @Test
    public void testSubmitChallenge_ThrowsExceptionWhenTicketDoesNotExist() {
        assertEquals(0, challengeRepository.count());

        SubmitChallengeRequest request = new SubmitChallengeRequest();
        request.setTicketId("ABC-XYZ");
        request.setReason("I was not at this location");

        assertThrows(ChallengeException.class, ()-> challengeService.submitChallenge(request));
        assertEquals(0, challengeRepository.count());
    }

    @Test
    public void testSubmitChallenge_ThrowsExceptionWhenReasonIsBlankAndDoesNotSave() {
        assertEquals(0, challengeRepository.count());
        Ticket ticket = new Ticket();
        ticket.setVehicleRegistrationNumber("XYZ-123XY");
        ticket.setOffenceCode("JOH");
        Ticket savedTicket = ticketRepository.save(ticket);

        SubmitChallengeRequest request = new SubmitChallengeRequest();
        request.setTicketId(savedTicket.getId());
        request.setReason(" ");

        assertThrows(ChallengeException.class, ()-> challengeService.submitChallenge(request));
        assertEquals(0, challengeRepository.count());
    }

    @Test
    public void testSubmitChallenge_ThrowsExceptionWhenReasonIsNullAndDoesNotSave() {
        assertEquals(0, challengeRepository.count());
        Ticket ticket = new Ticket();
        ticket.setVehicleRegistrationNumber("XYZ-123XY");
        ticket.setOffenceCode("JOH");
        Ticket savedTicket = ticketRepository.save(ticket);

        SubmitChallengeRequest request = new SubmitChallengeRequest();
        request.setTicketId(savedTicket.getId());
        request.setReason(null);

        assertThrows(ChallengeException.class, ()-> challengeService.submitChallenge(request));
        assertEquals(0, challengeRepository.count());
    }

    @Test
    public void testFindChallengeById_ReturnsIdWhenFound() {
        Challenge challenge = new Challenge();
        challenge.setTicketId("ABC-XYZ");
        challenge.setReason("I was not at this location");
        challenge.setStatus("PENDING");
        challenge.setSubmittedAt(LocalDate.now());

        Challenge savedChallenge = challengeRepository.save(challenge);

        SubmitChallengeResponse response = challengeService.findChallengeById(savedChallenge.getId());

        assertEquals(savedChallenge.getId(), response.getId());
        assertEquals("ABC-XYZ", response.getTicketId());
        assertEquals("I was not at this location", response.getReason());
        assertEquals("PENDING", response.getStatus());
    }

    @Test
    public void testFindChallengeById_ThrowsExceptionWhenNotFound() {
        assertThrows(ChallengeException.class, ()-> challengeService.findChallengeById("ABC-XYZ"));
    }

}