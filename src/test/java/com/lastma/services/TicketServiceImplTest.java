package com.lastma.services;

import com.lastma.data.models.Offence;
import com.lastma.data.models.Ticket;
import com.lastma.data.models.Vehicle;
import com.lastma.data.repositories.OffenceRepository;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.response.IssueTicketResponse;
import com.lastma.exceptions.TicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketServiceImplTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private OffenceRepository offenceRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @BeforeEach
    public void setUp() {
        ticketRepository.deleteAll();
        vehicleRepository.deleteAll();
        offenceRepository.deleteAll();
    }

    @Test
    public void testIssueTicket_SavesTicket() {
        assertEquals(0, ticketRepository.count());
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber("XYZ-123XY");
        vehicle.setMake("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setColour("Black");
        vehicleRepository.save(vehicle);

        Offence offence = new Offence();
        offence.setCode("JOH");
        offence.setName("Traffic Control Violation");
        offence.setDescription("Disobeying traffic control personnel");
        offence.setActive(true);
        offenceRepository.save(offence);

        IssueTicketRequest request = new IssueTicketRequest();
        request.setVehicleRegistrationNumber("XYZ-123XY");
        request.setOffenceCode("JOH");

        IssueTicketResponse response = ticketService.issueTicket(request);

        assertNotNull(response.getId());
        assertEquals("XYZ-123XY", response.getVehicleRegistrationNumber());
        assertEquals("JOH", response.getOffenceCode());
        assertEquals(1, ticketRepository.count());
    }

    @Test
    public void testFindTicketById_ReturnsTicketWhenFound() {
        Ticket ticket = new Ticket();
        ticket.setVehicleRegistrationNumber("XYZ-123XY");
        ticket.setOffenceCode("JOH");
        Ticket savedTicket = ticketRepository.save(ticket);

        IssueTicketResponse response = ticketService.findTicketById(savedTicket.getId());
        assertEquals(savedTicket.getId(), response.getId());
        assertEquals("XYZ-123XY", response.getVehicleRegistrationNumber());
        assertEquals("JOH", response.getOffenceCode());
    }

    @Test
    public void testFindTicketById_ThrowsExceptionWhenNotFound() {
        assertThrows(TicketException.class, ()-> ticketService.findTicketById("343DES-fats"));
    }

    @Test
    public void testFindTicketsByVehicleRegistrationNumber_returnsTicketsForThatVehicle() {
        Ticket firstTicket = new Ticket();
        firstTicket.setVehicleRegistrationNumber("XYZ-123XY");
        firstTicket.setOffenceCode("JOH");
        ticketRepository.save(firstTicket);

        Ticket sameVehicleTicket = new Ticket();
        sameVehicleTicket.setVehicleRegistrationNumber("XYZ-123XY");
        sameVehicleTicket.setOffenceCode("SPD");
        ticketRepository.save(sameVehicleTicket);

        Ticket anotherVehicleTicket = new Ticket();
        anotherVehicleTicket.setVehicleRegistrationNumber("ABC-123");
        anotherVehicleTicket.setOffenceCode("YUP");
        ticketRepository.save(anotherVehicleTicket);

        List<IssueTicketResponse> tickets = ticketService.findTicketsByVehicleRegistrationNumber("XYZ-123XY");
        assertEquals(2, tickets.size());

        for(IssueTicketResponse ticket: tickets) {
            assertEquals("XYZ-123XY", ticket.getVehicleRegistrationNumber());
        }
    }
}