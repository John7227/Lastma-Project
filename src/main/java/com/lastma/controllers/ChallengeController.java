package com.lastma.controllers;

import com.lastma.dtos.requests.SubmitChallengeRequest;
import com.lastma.dtos.requests.UpdateChallengeStatusRequest;
import com.lastma.dtos.response.ApiResponse;
import com.lastma.services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/Submit")
    public ResponseEntity<?> submitChallenge(@RequestBody SubmitChallengeRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, challengeService.submitChallenge(request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Id")
    public ResponseEntity<?> findChallengeById(@RequestParam String id) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, challengeService.findChallengeById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Ticket")
    public ResponseEntity<?> findChallengesByTicketId(@RequestParam String ticketId) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, challengeService.findChallengesByTicketId(ticketId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllChallenges() {
        try{
            return new ResponseEntity<>(new ApiResponse(true, challengeService.findAllChallenges()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/Id")
    public ResponseEntity<?> updateChallengeStatus(@RequestParam String id, @RequestBody UpdateChallengeStatusRequest request) {
        try{
            return new ResponseEntity<>(new ApiResponse(true, challengeService.updateChallengeStatus(id, request)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
