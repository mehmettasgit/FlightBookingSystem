package com.mehmet.FlightBookingSystem.controller;


import com.mehmet.FlightBookingSystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
