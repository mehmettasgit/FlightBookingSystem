package com.mehmet.FlightBookingSystem.model.mapper.repository;

import com.mehmet.FlightBookingSystem.model.entity.AirportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportCompanyRepository extends JpaRepository<AirportCompany, Integer> {

}
