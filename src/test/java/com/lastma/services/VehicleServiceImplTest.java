package com.lastma.services;

import com.lastma.data.repositories.VehicleRepository;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.response.RegisterVehicleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class VehicleServiceImplTest {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void testThatRegisterVehicle_SavesVehicle_AndCountIsOne() {
        vehicleRepository.deleteAll();
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest request = new RegisterVehicleRequest();
        request.setRegistrationNumber("XYZ-123XY");
        request.setMake("Toyota");
        request.setMode("Corolla");
        request.setColour("Black");

        RegisterVehicleResponse savedVehicle = vehicleService.registerVehicle(request);
        assertNotNull(savedVehicle.getId());
        assertEquals("XYZ-123XY", savedVehicle.getRegistrationNumber());
        assertEquals(1, vehicleRepository.count());
    }

}