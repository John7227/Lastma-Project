package com.lastma.services;

import com.lastma.data.models.Payment;
import com.lastma.data.models.Ticket;
import com.lastma.data.repositories.PaymentRepository;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.dtos.requests.MakePaymentRequest;
import com.lastma.dtos.response.MakePaymentResponse;
import com.lastma.exceptions.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lastma.utils.PaymentMapper.map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public MakePaymentResponse makePayment(MakePaymentRequest request) {
        Optional<Ticket> result = ticketRepository.findById(request.getTicketId());
        if(result.isEmpty()) {
            throw new PaymentException("Ticket not found");
        }
        Ticket ticket = result.get();

        if(request.getPaymentMethod() == null || request.getPaymentMethod().isBlank()) {
            throw new PaymentException("Payment method is required");
        }

        Payment payment = new Payment();
        payment.setTicketId(ticket.getId());
        payment.setPaymentMethod(request.getPaymentMethod());

        Payment savedPayment = paymentRepository.save(payment);
        return map(savedPayment);
    }

}
