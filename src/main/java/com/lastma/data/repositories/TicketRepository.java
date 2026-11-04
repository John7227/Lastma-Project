package com.lastma.data.repositories;

import com.lastma.data.models.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findByVehicleRegistrationNumber(String vehicleRegistrationNumber);
}
