package com.mehmet.FlightBookingSystem.model.mapper.repository;

import com.mehmet.FlightBookingSystem.model.entity.AirportCompany;
import com.mehmet.FlightBookingSystem.model.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Optional <Passenger> findById(Integer id);

    Optional <Passenger> findByEmail(String email);

    Optional <Passenger> findByPhone(String phone);

}
