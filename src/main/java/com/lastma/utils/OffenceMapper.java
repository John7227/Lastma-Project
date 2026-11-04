package com.lastma.utils;

import com.lastma.data.models.Offence;
import com.lastma.dtos.requests.AddOffenceRequest;
import com.lastma.dtos.response.AddOffenceResponse;

public class OffenceMapper {

    public static Offence map(AddOffenceRequest request) {
        Offence newOffence = new Offence();
        newOffence.setCode(request.getCode());
        newOffence.setName(request.getName());
        newOffence.setDescription(request.getDescription());
        newOffence.setPenaltyPoints(request.getPenaltyPoints());
        newOffence.setFirstOffenceFine(request.getFirstOffenceFine());
        newOffence.setSubsequentOffenceFine(request.getSubsequentOffenceFine());
        newOffence.setAdditionalPenaltyDescription(request.getAdditionalPenaltyDescription());
        newOffence.setViolationType(request.getViolationType());
        return newOffence;
    }

    public static AddOffenceResponse map(Offence offence) {
        AddOffenceResponse response = new AddOffenceResponse();
        response.setId(offence.getId());
        response.setCode(offence.getCode());
        response.setName(offence.getName());
        response.setDescription(offence.getDescription());
        response.setPenaltyPoints(offence.getPenaltyPoints());
        response.setFirstOffenceFine(offence.getFirstOffenceFine());
        response.setSubsequentOffenceFine(offence.getSubsequentOffenceFine());
        response.setAdditionalPenaltyDescription(offence.getAdditionalPenaltyDescription());
        response.setActive(offence.isActive());
        response.setViolationType(offence.getViolationType());
        return response;
    }
}
