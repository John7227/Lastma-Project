package com.lastma.controllers;

import com.lastma.dtos.requests.AddOffenceRequest;
import com.lastma.dtos.response.ApiResponse;
import com.lastma.services.OffenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Offence")
public class OffenceController {

    @Autowired
    private OffenceService offenceService;

    @PostMapping("/Add")
    public ResponseEntity<?> addOffence(@RequestBody AddOffenceRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, offenceService.addOffence(request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Code")
    public ResponseEntity<?> findOffenceByCode(@RequestParam String code) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, offenceService.findOffenceByCode(code)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllOffences() {
        try{
            return new ResponseEntity<>(new ApiResponse(true, offenceService.findAllOffences()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/Deactivate")
    public ResponseEntity<?> deactivateOffence(@RequestParam String code) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, offenceService.deactivateOffence(code)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/Activate")
    public ResponseEntity<?> activateOffence(@RequestParam String code) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, offenceService.activateOffence(code)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
