package com.mehmet.FlightBookingSystem.service.iml;

import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.AirportCompany;
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
        List<Passenger> allPassengers = passengerRepository.findAll();
        if(allPassengers.isEmpty()){
            throw new NotFoundException("There is no Passenger in Database");
        }
        return allPassengers;
    }

    @Override
    public Passenger getPassenger(Integer id) {
      Passenger passenger = passengerRepository.findById(id).orElse(null);
      if(id == null){
          throw new NotFoundException(id +"Id is not in the database");
      }
      return passenger;
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
