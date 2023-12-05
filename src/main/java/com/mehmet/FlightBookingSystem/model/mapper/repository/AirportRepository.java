package com.mehmet.FlightBookingSystem.model.mapper.repository;

import com.mehmet.FlightBookingSystem.model.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
