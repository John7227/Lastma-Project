package com.lastma.dtos.requests;

import lombok.Data;

@Data
public class MakePaymentRequest {

    private String ticketId;
    private String paymentMethod;
}
