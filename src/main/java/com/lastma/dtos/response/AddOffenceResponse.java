package com.lastma.dtos.response;

import com.lastma.data.enums.ViolationType;
import lombok.Data;

@Data
public class AddOffenceResponse {

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
