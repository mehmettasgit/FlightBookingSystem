package com.mehmet.FlightBookingSystem.service.iml;

import com.mehmet.FlightBookingSystem.model.entity.Airport;
import com.mehmet.FlightBookingSystem.model.mapper.repository.AirportRepository;
import com.mehmet.FlightBookingSystem.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirortServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirport(Integer airportID) {
        return airportRepository.findById(airportID).orElse(null);
    }

    @Override
    public void addAirport(Airport airport) {
        airportRepository.save(airport);
    }

    @Override
    public void updateAirport(Integer airportId, Airport updatedAirport) {
        getAirport(airportId);
        updatedAirport.setId(airportId);
        airportRepository.save(updatedAirport);
    }

    @Override
    public void deleteAirport(Integer aiportID) {
        airportRepository.delete(getAirport(aiportID));
    }
}
