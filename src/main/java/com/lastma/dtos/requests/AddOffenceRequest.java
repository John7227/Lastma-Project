package com.lastma.dtos.requests;

import com.lastma.data.enums.ViolationType;
import lombok.Data;

@Data
public class AddOffenceRequest {

    private String code;
    private String name;
    private String description;
    private int penaltyPoints;
    private int firstOffenceFine;
    private ViolationType violationType;
    private int subsequentOffenceFine;
    private String additionalPenaltyDescription;

}
