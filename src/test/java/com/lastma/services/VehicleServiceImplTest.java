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
        request.setModel("Corolla");
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
        firstVehicle.setModel("Corolla");
        firstVehicle.setColour("Black");
        vehicleService.registerVehicle(firstVehicle);

        RegisterVehicleRequest duplicateVehicle = new RegisterVehicleRequest();
        duplicateVehicle.setRegistrationNumber("XYZ-123XY");
        duplicateVehicle.setMake("Honda");
        duplicateVehicle.setModel("Civic");
        duplicateVehicle.setColour("White");

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(duplicateVehicle));
        assertEquals(1, vehicleRepository.count());
    }

    @Test
    public void testRegisterVehicleWithoutARegistrationNumber_ThrowsException() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest request = new RegisterVehicleRequest();
        request.setRegistrationNumber(" ");
        request.setMake("Toyota");
        request.setModel("Corolla");
        request.setColour("Black");

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(request));
        assertEquals(0, vehicleRepository.count());
    }

    @Test
    public void testRegisterVehicleWhenTheRegistrationNumberIsNull_ThrowsException() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest request = new RegisterVehicleRequest();
        request.setRegistrationNumber(null);
        request.setMake("Toyota");
        request.setModel("Corolla");
        request.setColour("Black");

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(request));
        assertEquals(0, vehicleRepository.count());
    }

    @Test
    public void testRegisterVehicleWithoutAMake_ThrowsException() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest request = new RegisterVehicleRequest();
        request.setRegistrationNumber("XYZ-123XY");
        request.setMake(" ");
        request.setModel("Corolla");
        request.setColour("Black");

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(request));
        assertEquals(0, vehicleRepository.count());
    }

    @Test
    public void testRegisterVehicleWhenTheMakeIsNull_ThrowsException() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest request = new RegisterVehicleRequest();
        request.setRegistrationNumber("XYZ-123XY");
        request.setMake(null);
        request.setModel("Corolla");
        request.setColour("Black");

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(request));
        assertEquals(0, vehicleRepository.count());
    }

    @Test
    public void testRegisterVehicleWithoutAModel_ThrowsException() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest request = new RegisterVehicleRequest();
        request.setRegistrationNumber("XYZ-123XY");
        request.setMake("Toyota");
        request.setModel(" ");
        request.setColour("Black");

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(request));
        assertEquals(0, vehicleRepository.count());
    }

    @Test
    public void testRegisterVehicleWhenTheModelIsNull_ThrowsException() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest request = new RegisterVehicleRequest();
        request.setRegistrationNumber("XYZ-123XY");
        request.setMake("Toyota");
        request.setModel(null);
        request.setColour("Black");

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(request));
        assertEquals(0, vehicleRepository.count());
    }

    @Test
    public void testRegisterVehicleWithoutAColour_ThrowsException() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest request = new RegisterVehicleRequest();
        request.setRegistrationNumber("XYZ-123XY");
        request.setMake("Toyota");
        request.setModel("Corolla");
        request.setColour(" ");

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(request));
        assertEquals(0, vehicleRepository.count());
    }

    @Test
    public void testRegisterVehicleWhenTheColourIsNull_ThrowsException() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest request = new RegisterVehicleRequest();
        request.setRegistrationNumber("XYZ-123XY");
        request.setMake("Toyota");
        request.setModel("Corolla");
        request.setColour(null);

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(request));
        assertEquals(0, vehicleRepository.count());
    }
}