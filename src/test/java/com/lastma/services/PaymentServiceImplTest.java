package com.lastma.services;

import com.lastma.data.models.Ticket;
import com.lastma.data.repositories.PaymentRepository;
import com.lastma.data.repositories.TicketRepository;
import com.lastma.dtos.requests.MakePaymentRequest;
import com.lastma.dtos.response.MakePaymentResponse;
import com.lastma.exceptions.PaymentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        ticketRepository.deleteAll();
        paymentRepository.deleteAll();
    }

    @Test
    public void testMakePayment_SavesPaymentForExistingTicket() {
        assertEquals(0, paymentRepository.count());

        Ticket ticket = new Ticket();
        ticket.setVehicleRegistrationNumber("XYZ-123XY");
        ticket.setOffenceCode("JOH");
        Ticket savedTicket = ticketRepository.save(ticket);

        MakePaymentRequest request = new MakePaymentRequest();
        request.setTicketId(savedTicket.getId());
        request.setPaymentMethod("CARD");

        MakePaymentResponse response = paymentService.makePayment(request);

        assertNotNull(response.getId());
        assertEquals(savedTicket.getId(), response.getTicketId());
        assertEquals("CARD", response.getPaymentMethod());
        assertEquals(1, paymentRepository.count());
    }

    @Test
    public void testMakePayment_ThrowsException_IfPaymentMethodIsEmpty() {
        assertEquals(0, paymentRepository.count());

        Ticket ticket = new Ticket();
        ticket.setVehicleRegistrationNumber("XYZ-123XY");
        ticket.setOffenceCode("JOH");
        Ticket savedTicket = ticketRepository.save(ticket);

        MakePaymentRequest request = new MakePaymentRequest();
        request.setTicketId(savedTicket.getId());
        request.setPaymentMethod(" ");

        assertThrows(PaymentException.class, ()-> paymentService.makePayment(request));
        assertEquals(0, paymentRepository.count());
    }

    @Test
    public void testMakePayment_ThrowsException_IfPaymentMethodIsNull() {
        assertEquals(0, paymentRepository.count());

        Ticket ticket = new Ticket();
        ticket.setVehicleRegistrationNumber("XYZ-123XY");
        ticket.setOffenceCode("JOH");
        Ticket savedTicket = ticketRepository.save(ticket);

        MakePaymentRequest request = new MakePaymentRequest();
        request.setTicketId(savedTicket.getId());
        request.setPaymentMethod(null);

        assertThrows(PaymentException.class, ()-> paymentService.makePayment(request));
        assertEquals(0, paymentRepository.count());
    }

    @Test
    public void testMakePaymentForTicketThatDoesNotExist_ThrowsException() {
        MakePaymentRequest request = new MakePaymentRequest();
        request.setTicketId("ABC-4556gv");
        request.setPaymentMethod("CARD");

        assertThrows(PaymentException.class, ()-> paymentService.makePayment(request));
        assertEquals(0, paymentRepository.count());
    }
}