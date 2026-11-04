package com.lastma.controllers;

import com.lastma.dtos.requests.MakePaymentRequest;
import com.lastma.dtos.response.ApiResponse;
import com.lastma.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/Make")
    public ResponseEntity<?> makePayment(@RequestBody MakePaymentRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, paymentService.makePayment(request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Id")
    public ResponseEntity<?> findPaymentById(@RequestParam String id) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, paymentService.findPaymentById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Ticket")
    public ResponseEntity<?> findPaymentsByTicketId(@RequestParam String ticketId) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, paymentService.findPaymentsByTicketId(ticketId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllPayments() {
        try{
            return new ResponseEntity<>(new ApiResponse(true, paymentService.findAllPayments()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
