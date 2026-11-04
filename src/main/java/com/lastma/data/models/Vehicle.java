package com.lastma.data.models;

import com.lastma.data.enums.VehicleType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Year;

@Data
@Document
public class Vehicle {

    @Id
    private String id;
    private String make;
    private String model;
    private Year year;
    private VehicleType vehicleType;
    private String driverId;
    private String plateNumber;
    private String colour;
    private String registrationNumber;
}
