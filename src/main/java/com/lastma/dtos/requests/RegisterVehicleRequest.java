package com.lastma.dtos.requests;

import com.lastma.data.enums.VehicleType;
import lombok.Data;

@Data
public class RegisterVehicleRequest {

    private String registrationNumber;
    private String make;
    private String model;
    private String colour;
    private VehicleType vehicleType;
}
