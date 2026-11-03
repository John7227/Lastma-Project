package com.lastma.utils;

import com.lastma.data.models.Vehicle;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.response.RegisterVehicleResponse;

public class VehicleMapper {

    public static Vehicle map(RegisterVehicleRequest request) {
        Vehicle newVehicle = new Vehicle();
        newVehicle.setRegistrationNumber(request.getRegistrationNumber());
        newVehicle.setMake(request.getMake());
        newVehicle.setModel(request.getModel());
        newVehicle.setColour(request.getColour());
        return newVehicle;
    }

    public static RegisterVehicleResponse map(Vehicle vehicle) {
        RegisterVehicleResponse response = new RegisterVehicleResponse();
        response.setId(vehicle.getId());
        response.setRegistrationNumber(vehicle.getRegistrationNumber());
        response.setMake(vehicle.getMake());
        response.setModel(vehicle.getModel());
        response.setColour(vehicle.getColour());
        return response;
    }
}
