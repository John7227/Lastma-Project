package com.lastma.validator;

import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.exceptions.OfficerException;

public class OfficerRequestValidator {

    public static void validator(RegisterOfficerRequest request) {
        if(request.getFullName() == null || request.getFullName().isBlank()) {
            throw new OfficerException("Name is required");
        }

        String email = request.getEmail();

        if(email == null || email.isBlank() || !email.contains("@") || !email.contains(".")) {
            throw new OfficerException("Enter a valid email");
        }
    }
}
