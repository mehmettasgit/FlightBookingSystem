package com.mehmet.FlightBookingSystem.model.mapper.repository;

import com.mehmet.FlightBookingSystem.model.entity.Passenger;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
