package com.lastma.validator;

import com.lastma.dtos.requests.AddOffenceRequest;
import com.lastma.exceptions.OffenceException;

public class OffenceRequestValidator {

    public static void validator(AddOffenceRequest request) {
        if(request.getCode() == null || request.getCode().isBlank()) {
            throw new OffenceException("Code is required");
        }
        if(request.getName() == null || request.getName().isBlank()) {
            throw new OffenceException("Name is required");
        }
        if(request.getDescription() == null || request.getDescription().isBlank()) {
            throw new OffenceException("Description is required");
        }
    }
}
