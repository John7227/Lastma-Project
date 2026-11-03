package com.lastma.services;

import com.lastma.data.models.Vehicle;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.response.RegisterVehicleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public RegisterVehicleResponse registerVehicle(RegisterVehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(request.getRegistrationNumber());
        vehicle.setMake(request.getMake());
        vehicle.setModel(request.getMode());
        vehicle.setColour(request.getColour());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        RegisterVehicleResponse response = new RegisterVehicleResponse();
        response.setId(savedVehicle.getId());
        response.setRegistrationNumber(savedVehicle.getRegistrationNumber());
        response.setMake(savedVehicle.getMake());
        response.setMode(savedVehicle.getModel());
        response.setColour(savedVehicle.getColour());

        return response;
    }
}
