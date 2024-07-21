package com.mehmet.FlightBookingSystem.controller;

import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.dto.AirportDTO;
import com.mehmet.FlightBookingSystem.model.entity.Airport;
import com.mehmet.FlightBookingSystem.model.mapper.AirportMapper;
import com.mehmet.FlightBookingSystem.service.AirportService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Valid
@Validated
@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {

    @Autowired
    private final AirportService airportService;

    @GetMapping(path = "/welcome")
    public String welcome() {
        return "Welcome to Airport Service";
    }


    @GetMapping(path = "/get_AllAirports")
    public ResponseEntity<?> getAllAirports() {
        try {
            List<AirportDTO> airports = airportService.getAllAirports();
            return new ResponseEntity<>(airports, HttpStatus.OK);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @GetMapping(path = "get_airport_byid/{airportID}")
    public ResponseEntity<?> getAirport(@PathVariable @Min(1) Integer airportID) {
        try {
            AirportDTO airport = airportService.getAirport(airportID);
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/add_airport")
    public ResponseEntity<Map<String, String>> addAirport(@Valid @RequestBody AirportDTO airport) {
     try {
           airportService.addAirport(AirportMapper.toEntity(airport));
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

    @PutMapping(path = "/update_airport/{airportID}")
    public ResponseEntity<Void> updateAirport(@PathVariable Integer airportID,
                                              @RequestBody @Valid Airport updatedAirport)
    {
        airportService.updateAirport(airportID, updatedAirport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete_airport/{airportID}")
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
