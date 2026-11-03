package com.lastma.controllers;

import com.lastma.dtos.requests.RegisterVehicleRequest;
import com.lastma.dtos.response.ApiResponse;
import com.lastma.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/Register")
    public ResponseEntity<?> registerVehicle(@RequestBody RegisterVehicleRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, vehicleService.registerVehicle(request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Id")
    public ResponseEntity<?> findVehicleById(@RequestParam String id) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, vehicleService.findVehicleById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/RegistrationNumber")
    public ResponseEntity<?> findVehicleByRegistrationNumber(@RequestParam String registrationNumber) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, vehicleService.findVehicleByRegistrationNumber(registrationNumber)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
