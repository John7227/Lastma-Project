package com.lastma.dtos.requests;

import lombok.Data;

@Data
public class RegisterVehicleRequest {

    private String registrationNumber;
    private String make;
    private String mode;
    private String colour;
}
