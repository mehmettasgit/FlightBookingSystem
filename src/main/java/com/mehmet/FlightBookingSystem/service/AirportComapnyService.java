package com.mehmet.FlightBookingSystem.service;

import com.mehmet.FlightBookingSystem.model.entity.AirportCompany;

import java.util.List;

public interface AirportComapnyService {

    List<AirportCompany> getAllAirportCompanies();

    AirportCompany getAirportCompany(Integer id);

    void addAirportCompany(AirportCompany airportCompany);

    void updateAirportCompany(Integer airportCompanyId, AirportCompany airportCompany);

    void deleteAirportCompany(Integer airportCompanyId);


}
