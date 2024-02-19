package com.mehmet.FlightBookingSystem.controller;

import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.Airport;
import com.mehmet.FlightBookingSystem.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping(path = "/welcome")
    public String welcome() {
        return "Welcome to Airport Service";
    }


    @GetMapping(path = "/getAllAirports")
    public ResponseEntity<?> getAllAirports() {
        try {
            List<Airport> airports = airportService.getAllAirports();
            return new ResponseEntity<>(airports, HttpStatus.OK);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{airportID}")
    public ResponseEntity<?> getAirport(@PathVariable Integer airportID) {
        try {
            Airport airport = airportService.getAirport(airportID);
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/add_airport")
    public ResponseEntity<Map<String, String>> addAirport(@Valid @RequestBody Airport airport) {
       try {
           airportService.addAirport(airport);
           System.out.println("Data is added - Airport Name: " + airport.getName());
           // Json result is created
           Map<String, String> response = new HashMap<>();
           response.put("status", "success");
           response.put("message", "Data is added - Airport Name: " + airport.getName());
           return new ResponseEntity<>(response, HttpStatus.CREATED);
       }
       catch (IllegalArgumentException e){
           // Hata durumunda uygun HTTP yanıtını ve hata mesajını döndür
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping(path = "/{airportID}")
    public ResponseEntity<Void> updateAirport(@PathVariable Integer airportID, @RequestBody Airport updatedAirport) {
        airportService.updateAirport(airportID, updatedAirport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{airportId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Integer airportId) {
        try {
            airportService.deleteAirport(airportId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
