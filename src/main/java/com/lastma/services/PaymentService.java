package com.lastma.services;

import com.lastma.dtos.requests.MakePaymentRequest;
import com.lastma.dtos.response.MakePaymentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    MakePaymentResponse makePayment(MakePaymentRequest request);
    MakePaymentResponse findPaymentById(String id);
    List<MakePaymentResponse> findPaymentsByTicketId(String ticketId);
    List<MakePaymentResponse> findAllPayments();
}
