package com.lastma.data.models;

import com.lastma.data.enums.ViolationType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Offence {

    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private int penaltyPoints;
    private int firstOffenceFine;
    private ViolationType violationType;
    private int subsequentOffenceFine;
    private String additionalPenaltyDescription;
    private boolean active;
}
