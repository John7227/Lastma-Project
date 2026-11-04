package com.lastma.services;

import com.lastma.dtos.requests.MakePaymentRequest;
import com.lastma.dtos.response.MakePaymentResponse;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    MakePaymentResponse makePayment(MakePaymentRequest request);
    MakePaymentResponse findPaymentById(String id);
}
