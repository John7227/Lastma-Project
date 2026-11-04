package com.lastma.services;

import com.lastma.data.models.Payment;
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

import java.util.List;

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

    @Test
    public void testFindPaymentById_ReturnsPaymentWhenFound() {
        assertEquals(0, paymentRepository.count());

        Ticket ticket = new Ticket();
        ticket.setVehicleRegistrationNumber("XYZ-123XY");
        ticket.setOffenceCode("JOH");
        Ticket savedTicket = ticketRepository.save(ticket);

        MakePaymentRequest request = new MakePaymentRequest();
        request.setTicketId(savedTicket.getId());
        request.setPaymentMethod("CARD");

        MakePaymentResponse savedPayment = paymentService.makePayment(request);

        MakePaymentResponse response = paymentService.findPaymentById(savedPayment.getId());

        assertEquals("CARD", response.getPaymentMethod());
        assertEquals(1, paymentRepository.count());
    }

    @Test
    public void testFindPaymentById_ThrowsException_WhenNotFound() {
        assertThrows(PaymentException.class, ()-> paymentService.findPaymentById("ABC-6goat"));
    }

    @Test
    public void testFindPaymentByTicketId_ReturnsPaymentsForThatTicket() {
        assertEquals(0, paymentRepository.count());

        Payment firstPayment = new Payment();
        firstPayment.setTicketId("ticket-123");
        firstPayment.setPaymentMethod("CARD");
        paymentRepository.save(firstPayment);

        Payment secondPayment = new Payment();
        secondPayment.setTicketId("ticket-123");
        secondPayment.setPaymentMethod("CASH");
        paymentRepository.save(secondPayment);

        Payment otherTicketPayment = new Payment();
        otherTicketPayment.setTicketId("ticket-456");
        otherTicketPayment.setPaymentMethod("CARD");
        paymentRepository.save(otherTicketPayment);

        List<MakePaymentResponse> payments = paymentService.findPaymentsByTicketId("ticket-123");

        assertEquals(2, payments.size());

        for(MakePaymentResponse payment: payments) {
            assertEquals("ticket-123", payment.getTicketId());
        }

    }

    @Test
    public void testFindPaymentByTicketId_ThrowsException_WhenNotFound() {
        assertThrows(PaymentException.class, ()-> paymentService.findPaymentsByTicketId("ABC-6goat"));
    }

    @Test
    public void testFindAllPayments_ReturnsAllPayments() {
        assertEquals(0, paymentRepository.count());

        Payment firstPayment = new Payment();
        firstPayment.setTicketId("ticket-123");
        firstPayment.setPaymentMethod("CARD");
        paymentRepository.save(firstPayment);

        Payment secondPayment = new Payment();
        secondPayment.setTicketId("ticket-123");
        secondPayment.setPaymentMethod("CASH");
        paymentRepository.save(secondPayment);

        List<MakePaymentResponse> payments = paymentService.findAllPayments();

        assertEquals(2, payments.size());
    }
}