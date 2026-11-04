package com.lastma.services;

import com.lastma.data.models.Challenge;
import com.lastma.data.models.Ticket;
import com.lastma.data.repositories.ChallengeRepository;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.dtos.requests.SubmitChallengeRequest;
import com.lastma.dtos.requests.UpdateChallengeStatusRequest;
import com.lastma.dtos.response.SubmitChallengeResponse;
import com.lastma.exceptions.ChallengeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

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
    public void testSubmitChallenge_savesChallengeForExistingTicket() {
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
    public void testSubmitChallenge_throwsExceptionWhenTicketDoesNotExist() {
        assertEquals(0, challengeRepository.count());

        SubmitChallengeRequest request = new SubmitChallengeRequest();
        request.setTicketId("ABC-XYZ");
        request.setReason("I was not at this location");

        assertThrows(ChallengeException.class, ()-> challengeService.submitChallenge(request));
        assertEquals(0, challengeRepository.count());
    }

    @Test
    public void testSubmitChallenge_throwsExceptionWhenReasonIsBlankAndDoesNotSave() {
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
    public void testSubmitChallenge_throwsExceptionWhenReasonIsNullAndDoesNotSave() {
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
    public void testFindChallengeById_returnsIdWhenFound() {
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
    public void testFindChallengeById_throwsExceptionWhenNotFound() {
        assertThrows(ChallengeException.class, ()-> challengeService.findChallengeById("ABC-XYZ"));
    }

    @Test
    public void testFindChallengesByTicketId_returnsChallengesForThatTicket() {
        Challenge firstChallenge = new Challenge();
        firstChallenge.setTicketId("ABC-XYZ");
        firstChallenge.setReason("I was not at this location");
        firstChallenge.setStatus("PENDING");
        challengeRepository.save(firstChallenge);

        Challenge secondChallenge = new Challenge();
        secondChallenge.setTicketId("ABC-XYZ");
        secondChallenge.setReason("The vehicle is not mine");
        secondChallenge.setStatus("PENDING");
        challengeRepository.save(secondChallenge);

        Challenge anotherTicket = new Challenge();
        anotherTicket.setTicketId("123-890");
        anotherTicket.setReason("Foolish reason");
        anotherTicket.setStatus("PENDING");
        challengeRepository.save(anotherTicket);

        List<SubmitChallengeResponse> responses = challengeService.findChallengesByTicketId("ABC-XYZ");

        assertEquals(2, responses.size());

        for(SubmitChallengeResponse response: responses) {
            assertEquals("ABC-XYZ", response.getTicketId());
        }

    }

    @Test
    public void testFindAllChallenges_ReturnsAllSavedChallenges() {
        Challenge firstChallenge = new Challenge();
        firstChallenge.setTicketId("ABC-XYZ");
        firstChallenge.setReason("I was not at this location");
        firstChallenge.setStatus("PENDING");
        challengeRepository.save(firstChallenge);

        Challenge secondChallenge = new Challenge();
        secondChallenge.setTicketId("ABC-XYZ");
        secondChallenge.setReason("The vehicle is not mine");
        secondChallenge.setStatus("PENDING");
        challengeRepository.save(secondChallenge);

        Challenge anotherTicket = new Challenge();
        anotherTicket.setTicketId("123-890");
        anotherTicket.setReason("Foolish reason");
        anotherTicket.setStatus("PENDING");
        challengeRepository.save(anotherTicket);

        List<SubmitChallengeResponse> responses = challengeService.findAllChallenges();

        assertEquals(3, responses.size());
    }

    @Test
    public void testUpdateChallengeStatus_updatesAndReturnsChallenge() {
        Challenge firstChallenge = new Challenge();
        firstChallenge.setTicketId("ABC-XYZ");
        firstChallenge.setReason("I was not at this location");
        firstChallenge.setStatus("PENDING");
        Challenge savedChallenge = challengeRepository.save(firstChallenge);

        UpdateChallengeStatusRequest request = new UpdateChallengeStatusRequest();
        request.setStatus("ACCEPTED");
        request.setDecisionNotes("The evidence supports the challenge");
        request.setReviewedByOfficerId("ABC-XYZ");

        SubmitChallengeResponse response = challengeService.updateChallengeStatus(savedChallenge.getId(), request);

        assertEquals("ACCEPTED", response.getStatus());
        assertEquals("The evidence supports the challenge", response.getDecisionNotes());
        assertEquals("ABC-XYZ", response.getReviewedByOfficerId());
    }

    @Test
    public void testUpdateChallengeStatus_throwsExceptionWhenChallengeDoesNotExist() {
        UpdateChallengeStatusRequest request = new UpdateChallengeStatusRequest();
        request.setStatus("ACCEPTED");
        request.setDecisionNotes("The evidence supports the challenge");
        request.setReviewedByOfficerId("ABC-XYZ");

        assertThrows(ChallengeException.class, ()-> challengeService.updateChallengeStatus("ABC-XYZ", request));
    }

}