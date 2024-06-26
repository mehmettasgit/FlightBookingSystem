package com.mehmet.FlightBookingSystem.controller;


import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.Airport;
import com.mehmet.FlightBookingSystem.model.entity.AirportCompany;
import com.mehmet.FlightBookingSystem.model.entity.Flight;
import com.mehmet.FlightBookingSystem.service.AirportComapnyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/airport-company")
public class AirportCompanyController {

    private final AirportComapnyService airportComapnyService;


    @GetMapping
    public String welcome(){
        return "Welcome to Airportcompany service";
    }

    @GetMapping(value = "/getAllCompanies")
    public ResponseEntity<?> getAllAirportCompanies(){
        try {
            List<AirportCompany> airportCompanies = airportComapnyService.getAllAirportCompanies();
            for (AirportCompany company : airportCompanies) {
                System.out.println(company); // Her bir şirketin bilgilerini konsola yazdır
            }
            return new ResponseEntity<>(airportCompanies, HttpStatus.OK);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{airportCompanyId}")
    public ResponseEntity<?> getAirportCompany(@PathVariable @Min(1) Integer airportCompanyId){
        try {
            AirportCompany airportCompany = airportComapnyService.getAirportCompany(airportCompanyId);
                System.out.println(airportCompany);
            return new ResponseEntity<>(airportCompany, HttpStatus.OK);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/addAirportCompany")
    public ResponseEntity<Map<String,String >> saveAirportCompany(@RequestBody AirportCompany airportCompany){
        airportComapnyService.addAirportCompany(airportCompany);
        System.out.println("Data is added - AirportCompany Name:" +airportCompany.getName());
        //Json result is created
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Data is added - Airport Name: " + airportCompany.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{airportCompanyID}")
    public ResponseEntity<AirportCompany> updateAirportCompany(@PathVariable Integer airportCompanyID, @RequestBody AirportCompany airportCompany){
        airportComapnyService.updateAirportCompany(airportCompanyID, airportCompany);
        AirportCompany updatedCompany = airportComapnyService.getAirportCompany(airportCompanyID); // Güncellenmiş şirket bilgisini al
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{airportCompanyId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Integer airportCompanyId) {
        try {
            airportComapnyService.deleteAirportCompany(airportCompanyId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

   @PostMapping(value = "/add-flight")
   public boolean addNewFlight(@RequestBody Flight flight){
       return airportComapnyService.addNewFlight(flight.getAirportCompany().getId(), flight.getId());
   }
}
