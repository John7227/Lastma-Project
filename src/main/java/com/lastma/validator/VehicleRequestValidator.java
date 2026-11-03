package com.lastma.validator;

import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.exceptions.VehicleException;

public class VehicleRequestValidator {

    public static void validator(RegisterVehicleRequest request) {
        if(request.getRegistrationNumber() == null || request.getRegistrationNumber().isBlank()) {
            throw new VehicleException("Registration Number is required");
        }
        if(request.getMake() == null || request.getMake().isBlank()) {
            throw new VehicleException("Make is required");
        }
        if(request.getModel() == null || request.getModel().isBlank()) {
            throw new VehicleException("Model is required");
        }
        if(request.getColour() == null || request.getColour().isBlank()) {
            throw new VehicleException("Colour is required");
        }
    }
}
