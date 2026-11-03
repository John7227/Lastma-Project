package com.lastma.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document
public class Payment {

    @Id
    private String id;
    private String ticketId;
    private String paymentReference;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
}
