package com.mehmet.FlightBookingSystem.model.repository;


import com.mehmet.FlightBookingSystem.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Flight getByCode(String code);

    List<Flight> getAllByDepartureDateBetween(Date startDate, Date endDate);

    List<Flight> getAllByAirportCompany_Name(String airportCompanyName);

}
