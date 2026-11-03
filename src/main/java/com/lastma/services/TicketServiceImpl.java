package com.lastma.services;

import com.lastma.data.models.Offence;
import com.lastma.data.models.Ticket;
import com.lastma.data.models.Vehicle;
import com.lastma.data.repositories.OffenceRepository;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.response.IssueTicketResponse;
import com.lastma.exceptions.OffenceException;
import com.lastma.exceptions.TicketException;
import com.lastma.exceptions.VehicleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lastma.utils.TicketMapper.map;

@Service
public class TicketServiceImpl implements  TicketService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private OffenceRepository offenceRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public IssueTicketResponse issueTicket(IssueTicketRequest request) {
        Optional<Vehicle> vehicleResult = vehicleRepository.findByRegistrationNumber(request.getVehicleRegistrationNumber());
        if(vehicleResult.isEmpty()) {
            throw new VehicleException("Vehicle not found");
        }
        Vehicle vehicle = vehicleResult.get();


        Optional<Offence> offenceResult = offenceRepository.findByCode(request.getOffenceCode());
        if(offenceResult.isEmpty()) {
            throw new OffenceException("Offence not found");
        }
        Offence offence = offenceResult.get();

        if(!offence.isActive()) {
            throw new OffenceException("This offence is not active");
        }

        Ticket ticket = new Ticket();
        ticket.setVehicleRegistrationNumber(vehicle.getRegistrationNumber());
        ticket.setOffenceCode(offence.getCode());

        Ticket savedTicket = ticketRepository.save(ticket);

        return map(savedTicket);
    }

    @Override
    public IssueTicketResponse findTicketById(String id) {
        Optional<Ticket> result = ticketRepository.findById(id);
        if(result.isEmpty()) {
            throw new TicketException("Ticket not found");
        }

        Ticket ticket = result.get();
        return map(ticket);
    }

}
