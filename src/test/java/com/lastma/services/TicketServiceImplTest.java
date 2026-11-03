package com.lastma.services;

import com.lastma.data.models.Offence;
import com.lastma.data.models.Vehicle;
import com.lastma.data.repositories.OffenceRepository;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.response.IssueTicketResponse;
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

}