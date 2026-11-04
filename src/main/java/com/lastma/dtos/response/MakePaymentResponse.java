package com.lastma.dtos.response;

import lombok.Data;

@Data
public class MakePaymentResponse {

    private String id;
    private String ticketId;
    private String paymentMethod;
}
