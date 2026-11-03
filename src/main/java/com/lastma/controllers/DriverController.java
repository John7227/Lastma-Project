package com.lastma.controllers;

import com.lastma.dtos.requests.LoginDriverRequest;
import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.dtos.response.ApiResponse;
import com.lastma.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/Register")
    public ResponseEntity<?> registerDriver(@RequestBody RegisterDriverRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, driverService.registerDriver(request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Email")
    public ResponseEntity<?> findDriverByEmail(@RequestParam String email) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, driverService.findDriverByEmail(email)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/Login")
    public ResponseEntity<?> loginDriver(@RequestBody LoginDriverRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, driverService.loginDriver(request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
