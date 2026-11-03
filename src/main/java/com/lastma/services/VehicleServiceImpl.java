package com.lastma.services;

import com.lastma.data.models.Vehicle;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.response.RegisterVehicleResponse;
import com.lastma.exceptions.VehicleException;
import com.lastma.validator.VehicleRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lastma.utils.VehicleMapper.map;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public RegisterVehicleResponse registerVehicle(RegisterVehicleRequest request) {

        VehicleRequestValidator.validator(request);
        if(vehicleRepository.existsByRegistrationNumber(request.getRegistrationNumber())) {
            throw new VehicleException("Vehicle is already registered");
        }

        Vehicle newVehicle = map(request);
        Vehicle savedVehicle = vehicleRepository.save(newVehicle);

        return map(savedVehicle);
    }

    @Override
    public RegisterVehicleResponse findVehicleById(String id) {
        Optional<Vehicle> result = vehicleRepository.findById(id);
        if(result.isEmpty()) {
            throw new VehicleException("Vehicle not found");
        }
        Vehicle vehicle = result.get();

        return map(vehicle);
    }

    @Override
    public RegisterVehicleResponse findVehicleByRegistrationNumber(String registrationNumber) {
        Optional<Vehicle> result = vehicleRepository.findByRegistrationNumber(registrationNumber);

        if(result.isEmpty()) {
            throw new VehicleException("Vehicle not found");
        }
        Vehicle vehicle = result.get();

        return map(vehicle);
    }

}
