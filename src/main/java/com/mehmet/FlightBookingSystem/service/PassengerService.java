package com.mehmet.FlightBookingSystem.service;

import com.mehmet.FlightBookingSystem.model.entity.Passenger;

import java.util.List;

public interface PassengerService {

    List<Passenger> getAllPassengers();

    Passenger getPassenger(Integer id);

    void addPassenger(Passenger passenger);

    Passenger updatePassenger(final Integer id, final Passenger passenger);

    boolean deletePassenger(Integer id);

    List<Passenger> getPassengersNameStartsWith(String prefix);

    List<Passenger> getPassengersSortedViaLastNameAsUpperCase();

}
