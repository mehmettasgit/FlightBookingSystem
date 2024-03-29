package com.mehmet.FlightBookingSystem.service;

import com.mehmet.FlightBookingSystem.model.entity.Flight;

import java.util.Date;
import java.util.List;

public interface FlightService {

    List<Flight> getAllFlights();

    Flight getFlight(Integer id);

    void addFlight(Flight flight);

    Flight updateFlight(Flight flight);

    boolean deleteFlight(Integer id);

    List<Flight> getAllFlightsDepartureDateBetween(Date start, Date end);

    Flight getFlightByCode(String code);

    List<Flight> getFlightNyAirportCompany(String code);
}
