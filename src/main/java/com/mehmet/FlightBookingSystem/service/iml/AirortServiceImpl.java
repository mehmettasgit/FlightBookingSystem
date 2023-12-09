package com.mehmet.FlightBookingSystem.service.iml;
import com.mehmet.FlightBookingSystem.exception.NotFoundException;
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
        List<Airport> airports = airportRepository.findAll();
        if (airports.isEmpty()) {
            throw new NotFoundException("There is no Airport in Database");
        }
        return airports;
    }

    @Override
    public Airport getAirport(Integer airportID) {
        Airport airport = airportRepository.findById(airportID).orElse(null);
        if (airport == null) {
            throw new NotFoundException(airportID + "Id is not in the Database");
        }
        return airport;
    }

    @Override
    public void addAirport(Airport airport) {
        //Airport airport1 = new Airport();
        if (airport != null &&
                airport.getName() != null && !airport.getName().isEmpty() &&
                airport.getAddress() != null && !airport.getAddress().isEmpty()) {

            // Eğer id alanı dolu ise hata fırlat
            if (airport.getId() != null) {
                throw new IllegalArgumentException("ID must be null for a new airport");
            }
            airportRepository.save(airport);
        } else {
            throw new IllegalArgumentException("Invalid airport data");
        }
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
