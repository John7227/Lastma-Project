package com.lastma.data.repositories;

import com.lastma.data.models.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByTicketId(String ticketId);
}
