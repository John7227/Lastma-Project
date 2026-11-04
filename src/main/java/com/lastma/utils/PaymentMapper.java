package com.lastma.utils;

import com.lastma.data.models.Payment;
import com.lastma.dtos.response.MakePaymentResponse;

public class PaymentMapper {

    public static MakePaymentResponse map(Payment payment) {
        MakePaymentResponse response = new MakePaymentResponse();
        response.setId(payment.getId());
        response.setTicketId(payment.getTicketId());
        response.setPaymentMethod(payment.getPaymentMethod());
        return response;
    }
}
