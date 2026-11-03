package com.lastma.services;

import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.response.RegisterVehicleResponse;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {

    RegisterVehicleResponse registerVehicle(RegisterVehicleRequest request);
    RegisterVehicleResponse findVehicleById(String id);
    RegisterVehicleResponse findVehicleByRegistrationNumber(String registrationNumber);
}
