package com.mehmet.FlightBookingSystem.model.repository;

import com.mehmet.FlightBookingSystem.model.entity.AirportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportCompanyRepository extends JpaRepository<AirportCompany, Integer> {
    Optional<AirportCompany> findByName(String name);

}
