package com.lastma.dtos.response;

import com.lastma.data.enums.VehicleType;
import lombok.Data;

@Data
public class RegisterVehicleResponse {

    private String id;
    private String registrationNumber;
    private String make;
    private String model;
    private String colour;
    private VehicleType vehicleType;
}
