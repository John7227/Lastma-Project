package com.lastma.controllers;

import com.lastma.dtos.requests.LoginOfficerRequest;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.response.ApiResponse;
import com.lastma.services.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Officer")
public class OfficerController {

    @Autowired
    private OfficerService officerService;

    @PostMapping("/Register")
    public ResponseEntity<?> registerOfficer(@RequestBody RegisterOfficerRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, officerService.registerOfficer(request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Id")
    public ResponseEntity<?> findOfficerById(@RequestParam String id) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, officerService.findOfficerById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/Login")
    public ResponseEntity<?> loginOfficer(@RequestBody LoginOfficerRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, officerService.loginOfficer(request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
