package com.mehmet.FlightBookingSystem.service.iml;

import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.Address;
import com.mehmet.FlightBookingSystem.model.dto.AirportDTO;
import com.mehmet.FlightBookingSystem.model.entity.Airport;
import com.mehmet.FlightBookingSystem.model.mapper.AirportMapper;
import com.mehmet.FlightBookingSystem.model.repository.AirportRepository;
import com.mehmet.FlightBookingSystem.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;


    @Override
    public List<AirportDTO> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        if (airports.isEmpty()) {
            throw new NotFoundException("There is no Airport in Database");
        }
        return airports.stream().map(AirportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AirportDTO getAirport(Integer airportID) {
        Optional<Airport> optionalAirport = airportRepository.findById(airportID);
        Airport airport = optionalAirport.orElseThrow(() -> new NotFoundException("Airport with ID " + airportID + " is not found"));
        return AirportMapper.toDTO(airport);
    }


    @Override
    public void addAirport(Airport airport) {
            List<Airport> existingAirports = airportRepository.findByName(airport.getName());
            if(!existingAirports.isEmpty()){
                throw new IllegalArgumentException("An airport with the same name already exists");
            }
            airportRepository.save(airport);
    }

   @Override
    public void updateAirport(Integer airportId, Airport updatedAirport) {
       getAirport(airportId);
        updatedAirport.setId(airportId);
        airportRepository.save(updatedAirport);
    }

    @Override
    public void deleteAirport(Integer airportID) {

        if (airportRepository.existsById(airportID)) {
            airportRepository.deleteById(airportID);
        } else {
            throw new EntityNotFoundException("Havaalanı bulunamadı, ID:" + airportID);
        }
    }

    /*private List<Address> getAddressCityStartsWith(String prefix){
        List<Airport> allAirports = getAllAirports();
        return allAirports.stream()
                .map(Airport::getAddress)
                .flatMap(Collection::stream);
    }*/
}
