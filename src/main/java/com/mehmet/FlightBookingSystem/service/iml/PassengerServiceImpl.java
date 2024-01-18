package com.mehmet.FlightBookingSystem.service.iml;

import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.Passenger;
import com.mehmet.FlightBookingSystem.model.mapper.repository.PassengerRepository;
import com.mehmet.FlightBookingSystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;


    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassenger(Integer id) {
        Optional<Passenger> byId = passengerRepository.findById(id);
        return byId.orElseThrow(()-> new NotFoundException("Passenger"));
    }

    @Override
    public void addPassenger(Passenger passenger) {
        passengerRepository.save(passenger);

    }

    @Override
    public Passenger updatePassenger(Integer id, Passenger passenger) {
        Passenger currPassenger = getPassenger(id);
        return passengerRepository.save(currPassenger);
    }

    @Override
    public boolean deletePassenger(Integer id) {
        passengerRepository.delete(getPassenger(id));
        return true;
    }

    @Override
    public List<Passenger> getPassengersNameStartsWith(String prefix) {
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream().filter(p->p.getFirstname().startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public List<Passenger> getPassengersSortedViaLastNameAsUpperCase() {
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .sorted(Comparator.comparing(Passenger::getLastname))
                .peek(p -> p.setLastname(p.getLastname().toUpperCase()))
                .collect(Collectors.toList());
    }
}
