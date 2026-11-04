package com.lastma.services;

import com.lastma.data.enums.VehicleType;
import com.lastma.data.repositories.VehicleRepository;
import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.response.RegisterVehicleResponse;
import com.lastma.exceptions.DuplicateVehicleException;
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
        request.setVehicleType(VehicleType.CAR);

        RegisterVehicleResponse savedVehicle = vehicleService.registerVehicle(request);
        assertNotNull(savedVehicle.getId());
        assertEquals("XYZ-123XY", savedVehicle.getRegistrationNumber());
        assertEquals(VehicleType.CAR, savedVehicle.getVehicleType());
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
        firstVehicle.setVehicleType(VehicleType.BUS);
        vehicleService.registerVehicle(firstVehicle);

        RegisterVehicleRequest duplicateVehicle = new RegisterVehicleRequest();
        duplicateVehicle.setRegistrationNumber("XYZ-123XY");
        duplicateVehicle.setMake("Honda");
        duplicateVehicle.setModel("Civic");
        duplicateVehicle.setColour("White");

        assertThrows(DuplicateVehicleException.class, ()-> vehicleService.registerVehicle(duplicateVehicle));
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
        request.setVehicleType(VehicleType.LORRY);

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
        request.setVehicleType(VehicleType.MINIBUS);

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
        request.setVehicleType(VehicleType.KOROKPE);

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
        request.setVehicleType(VehicleType.TIPPER);

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
        request.setVehicleType(VehicleType.VAN);

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
        request.setVehicleType(VehicleType.CAR);

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
        request.setVehicleType(VehicleType.VAN);

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
        request.setVehicleType(VehicleType.LORRY);

        assertThrows(VehicleException.class, ()-> vehicleService.registerVehicle(request));
        assertEquals(0, vehicleRepository.count());
    }

    @Test
    public void testFindVehicleById_ReturnsVehicle() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest firstVehicle = new RegisterVehicleRequest();
        firstVehicle.setRegistrationNumber("XYZ-123XY");
        firstVehicle.setMake("Toyota");
        firstVehicle.setModel("Corolla");
        firstVehicle.setColour("Black");
        firstVehicle.setVehicleType(VehicleType.MINIBUS);
        RegisterVehicleResponse registered = vehicleService.registerVehicle(firstVehicle);

        RegisterVehicleResponse foundVehicle = vehicleService.findVehicleById(registered.getId());
        assertEquals("XYZ-123XY", foundVehicle.getRegistrationNumber());
        assertEquals("Toyota", foundVehicle.getMake());
        assertEquals("Corolla", foundVehicle.getModel());
        assertEquals(VehicleType.MINIBUS, foundVehicle.getVehicleType());
        assertEquals(registered.getId(), foundVehicle.getId());
    }

    @Test
    public void testFindVehicleById_WhenNotFound_ThrowsException() {
        assertThrows(VehicleException.class, ()-> vehicleService.findVehicleById("507f1f77bcf86cd799439011"));

    }

    @Test
    public void testFindVehicleByRegistrationNumber_ReturnsVehicle() {
        assertEquals(0, vehicleRepository.count());

        RegisterVehicleRequest firstVehicle = new RegisterVehicleRequest();
        firstVehicle.setRegistrationNumber("XYZ-123XY");
        firstVehicle.setMake("Toyota");
        firstVehicle.setModel("Corolla");
        firstVehicle.setColour("Black");
        firstVehicle.setVehicleType(VehicleType.CAR);
        RegisterVehicleResponse registered = vehicleService.registerVehicle(firstVehicle);

        RegisterVehicleResponse foundVehicle = vehicleService.findVehicleByRegistrationNumber("XYZ-123XY");
        assertEquals("XYZ-123XY", foundVehicle.getRegistrationNumber());
        assertEquals("Toyota", foundVehicle.getMake());
        assertEquals("Corolla", foundVehicle.getModel());
        assertEquals(VehicleType.CAR, foundVehicle.getVehicleType());
        assertEquals(registered.getId(), foundVehicle.getId());
    }

    @Test
    public void testFindVehicleByRegistrationNumber_WhenNotFound_ThrowsException() {
        assertThrows(VehicleException.class, ()-> vehicleService.findVehicleByRegistrationNumber("XYZ-123XY"));

    }
}