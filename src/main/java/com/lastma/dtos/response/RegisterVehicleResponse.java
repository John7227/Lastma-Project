package com.lastma.dtos.response;

import lombok.Data;

@Data
public class RegisterVehicleResponse {

    private String id;
    private String registrationNumber;
    private String make;
    private String mode;
    private String colour;
}
