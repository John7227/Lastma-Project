package com.lastma.services;

import com.lastma.data.models.Vehicle;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.response.RegisterVehicleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lastma.utils.VehicleMapper.map;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public RegisterVehicleResponse registerVehicle(RegisterVehicleRequest request) {

        Vehicle newVehicle = map(request);
        Vehicle savedVehicle = vehicleRepository.save(newVehicle);

        return map(savedVehicle);
    }
}
