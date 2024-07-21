package com.mehmet.FlightBookingSystem.controller;
import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.dto.PassengerDTO;
import com.mehmet.FlightBookingSystem.model.entity.Passenger;
import com.mehmet.FlightBookingSystem.model.mapper.PassengerMapper;
import com.mehmet.FlightBookingSystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    private static final PassengerMapper PASSENGER_MAPPER = Mappers.getMapper(PassengerMapper.class);

    @GetMapping(value ="/welcome")
    public String welcome(){
        return "Welcome to Passenger Service!";
    }

    @GetMapping(value ="/allPassengers")
    public ResponseEntity<?> getAllPassengers(){
        try{
            List<Passenger> allPassengers = passengerService.getAllPassengers();
            List<PassengerDTO> allPassengerDTOs = allPassengers.stream()
                    .map(PASSENGER_MAPPER::toDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(allPassengerDTOs);
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
            PassengerDTO passengerDTO = PASSENGER_MAPPER.toDto(passenger); // Yolcu nesnesini DTO'ya dönüştür
            return new ResponseEntity<>(passengerDTO, HttpStatus.OK);
        }
        catch (NotFoundException e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/addPassenger")
    public ResponseEntity<?> savePassenger(@RequestBody Passenger passenger){
        passengerService.addPassenger(passenger);
        PassengerDTO passengerDTO = PASSENGER_MAPPER.toDto(passenger); // Yolcu nesnesini DTO'ya dönüştür

        return new ResponseEntity<>(passengerDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{passengerID}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable final Integer passengerID,
                                                     @Valid @RequestBody Passenger passenger){
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

    @GetMapping(value = "/searchByFirstName")
    public ResponseEntity<?> getPassengersByFirstNamePrefix(@RequestParam String prefix){
        try{
            List<Passenger> passengers = passengerService.getPassengersNameStartsWith(prefix);

            // Log the passengers before mapping to DTO
            System.out.println("Passengers: " + passengers);

            List<PassengerDTO> passengerDTOS = passengers.stream()
                    .map(PASSENGER_MAPPER::toDto)
                    .collect(Collectors.toList());

            // Log the DTOs
            System.out.println("PassengerDTOs: " + passengerDTOS);

            return new ResponseEntity<>(passengerDTOS, HttpStatus.OK);
        }
        catch(NotFoundException e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
