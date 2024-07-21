package com.mehmet.FlightBookingSystem.model.repository;

import com.mehmet.FlightBookingSystem.model.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    List<Airport> findByName(String name);
}
