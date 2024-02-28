package com.mehmet.FlightBookingSystem.service;

import com.mehmet.FlightBookingSystem.model.dto.AirportDTO;
import com.mehmet.FlightBookingSystem.model.entity.Airport;

import java.util.List;

public interface AirportService {

    List<AirportDTO> getAllAirports();

    AirportDTO getAirport(Integer airportID);

    void addAirport(Airport airport);

    void updateAirport(Integer airportId, Airport updatedAirport);

    void deleteAirport(Integer aiportID);

}
