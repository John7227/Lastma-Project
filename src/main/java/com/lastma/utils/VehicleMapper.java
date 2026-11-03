package com.lastma.utils;

import com.lastma.data.models.Vehicle;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.response.RegisterVehicleResponse;

public class VehicleMapper {

    public static Vehicle map(RegisterVehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(request.getRegistrationNumber());
        vehicle.setMake(request.getMake());
        vehicle.setModel(request.getMode());
        vehicle.setColour(request.getColour());
        return vehicle;
    }

    public static RegisterVehicleResponse map(Vehicle vehicle) {
        RegisterVehicleResponse response = new RegisterVehicleResponse();
        response.setId(vehicle.getId());
        response.setRegistrationNumber(vehicle.getRegistrationNumber());
        response.setMake(vehicle.getMake());
        response.setMode(vehicle.getModel());
        response.setColour(vehicle.getColour());
        return response;
    }
}
