package com.mehmet.FlightBookingSystem.model.mapper.repository;

import com.mehmet.FlightBookingSystem.model.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
}
