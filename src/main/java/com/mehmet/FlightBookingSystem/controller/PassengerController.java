package com.mehmet.FlightBookingSystem.controller;
import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.AirportCompany;
import com.mehmet.FlightBookingSystem.model.entity.Passenger;
import com.mehmet.FlightBookingSystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "/addPassenger")
    public ResponseEntity<Map<String,String>> savePassenger(@RequestBody Passenger passenger){
        passengerService.addPassenger(passenger);
        System.out.println("Passenger is added:" + passenger.getFirstname() + " " + passenger.getLastname());
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Data is added- Passenger Name: " + passenger.getFirstname() + " " + passenger.getLastname());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{passengerID}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable Integer passengerID, @RequestBody Passenger passenger){
        passengerService.updatePassenger(passengerID, passenger);
        Passenger updatedPassenger = passengerService.getPassenger(passengerID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Integer id){
        try {
            passengerService.deletePassenger(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
