package com.lastma.validator;

import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.exceptions.DriverException;

public class DriverRequestValidator {

    public static void validator(RegisterDriverRequest request) {
        if(request.getFullName() == null || request.getFullName().isBlank()) {
            throw new DriverException("Name is required");
        }

        String email = request.getEmail();
        if(email == null || email.isBlank() || !email.contains("@") || !email.contains(".")) {
            throw new DriverException("Enter a valid email");
        }

    }
}
