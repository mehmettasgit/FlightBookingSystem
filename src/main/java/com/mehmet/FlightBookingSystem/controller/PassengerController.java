package com.mehmet.FlightBookingSystem.controller;


import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.Passenger;
import com.mehmet.FlightBookingSystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping
    public String welcome(){
        return "Welcome to Passenger Service!";
    }

    @GetMapping(value ="/allPassengers")
    public ResponseEntity<?> getAllPassengers(){
        try{
            List<Passenger> allPassengers = passengerService.getAllPassengers();
            return new ResponseEntity<>(allPassengers, HttpStatus.OK);
        }
        catch(NotFoundException e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<?> getPassengerById(@PathVariable @Min(1) Integer id){
        try {
            Passenger passenger = passengerService.getPassenger(id);
            return new ResponseEntity<>(passenger, HttpStatus.OK);
        }
        catch (NotFoundException e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
