package com.lastma.controllers;

import com.lastma.dtos.requests.IssueTicketRequest;
import com.lastma.dtos.response.ApiResponse;
import com.lastma.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/Issue")
    public ResponseEntity<?> issueTicket(@RequestBody IssueTicketRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, ticketService.issueTicket(request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Id")
    public ResponseEntity<?> findTicketById(@RequestParam String id) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, ticketService.findTicketById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Vehicle")
    public ResponseEntity<?> findTicketsByVehicleRegistrationNumber(@RequestParam String registrationNumber) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, ticketService.findTicketsByVehicleRegistrationNumber(registrationNumber)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllTickets() {
        try{
            return new ResponseEntity<>(new ApiResponse(true, ticketService.findAllTickets()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


}
