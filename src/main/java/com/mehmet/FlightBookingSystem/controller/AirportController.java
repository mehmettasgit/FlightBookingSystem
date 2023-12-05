package com.mehmet.FlightBookingSystem.controller;


import com.mehmet.FlightBookingSystem.model.entity.Airport;
import com.mehmet.FlightBookingSystem.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping(path = "/welcome")
    public String welcome(){
        return "Welcome to Airport Service";
    }

    @GetMapping(path = "/getAllAirports")
    public List<Airport> getAllAirports(){
        return airportService.getAllAirports();
    }

    @GetMapping(path = "/{airportID}")
    public ResponseEntity<Airport> getAirport(@PathVariable Integer airportID){
        Airport airport = airportService.getAirport(airportID);
        if(airport != null){
            return new ResponseEntity<>(airport, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/add_airport")
    public ResponseEntity<Void> addAirport(@RequestBody Airport airport){
        airportService.addAirport(airport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/{airportID}")
    public ResponseEntity<Void> updateAirport(@PathVariable Integer airportID, @RequestBody Airport updatedAirport){
        airportService.updateAirport(airportID, updatedAirport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{airportId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Integer airportId){
        airportService.deleteAirport(airportId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
