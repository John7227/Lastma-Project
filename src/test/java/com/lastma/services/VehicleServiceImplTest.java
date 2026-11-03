package com.lastma.services;

import com.lastma.data.repositories.VehicleRepository;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.response.RegisterVehicleResponse;
import com.lastma.exceptions.VehicleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleServiceImplTest {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @BeforeEach
    public void setUp() {
        vehicleRepository.deleteAll();
    }

    @Test
    public void testThatRegisterVehicle_SavesVehicle_AndCountIsOne() {
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

    @Test
    public void testRegisterVehicleWithDuplicateRegistrationNumber_ThrowsException() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest firstVehicle = new RegisterVehicleRequest();
        firstVehicle.setRegistrationNumber("XYZ-123XY");
        firstVehicle.setMake("Toyota");
        firstVehicle.setMode("Corolla");
        firstVehicle.setColour("Black");
        vehicleService.registerVehicle(firstVehicle);

        RegisterVehicleRequest duplicateVehicle = new RegisterVehicleRequest();
        duplicateVehicle.setRegistrationNumber("XYZ-123XY");
        duplicateVehicle.setMake("Honda");
        duplicateVehicle.setMode("Civic");
        duplicateVehicle.setColour("White");

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(duplicateVehicle));
        assertEquals(1, vehicleRepository.count());
    }


}