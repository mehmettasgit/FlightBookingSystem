package com.mehmet.FlightBookingSystem.model.mapper;

import com.mehmet.FlightBookingSystem.model.dto.AirportDTO;
import com.mehmet.FlightBookingSystem.model.entity.Airport;

public class AirportMapper {

    public static AirportDTO toDTO(Airport airport){
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setName(airport.getName());
        airportDTO.setAddress(airport.formatToAddressList());
        return airportDTO;
    }

    public static Airport toEntity(AirportDTO airportDTO){
        Airport airport = new Airport();
        airport.setName(airportDTO.getName());
        airport.setAddress(airportDTO.formatAdresses());
        return airport;
    }
}
