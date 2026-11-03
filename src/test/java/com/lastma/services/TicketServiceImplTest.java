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
}