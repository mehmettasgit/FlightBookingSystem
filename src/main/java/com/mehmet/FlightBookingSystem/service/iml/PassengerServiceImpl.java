package com.mehmet.FlightBookingSystem.service.iml;
import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.Passenger;
import com.mehmet.FlightBookingSystem.model.mapper.PassengerMapper;
import com.mehmet.FlightBookingSystem.model.repository.PassengerRepository;
import com.mehmet.FlightBookingSystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    private static final PassengerMapper PASSENGER_MAPPER = Mappers.getMapper(PassengerMapper.class);


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
      Optional<Passenger> byID = passengerRepository.findById(id);
      return byID.orElseThrow(()-> new NotFoundException("Passenger"));
    }

    @Override
    public void addPassenger(Passenger passenger) {
        if( passenger != null){
            Optional<Passenger> existingEmail = passengerRepository.findByEmail(passenger.getEmail());
            Optional<Passenger> existingPhoneNumber = passengerRepository.findByPhone(passenger.getPhone());
            if (existingPhoneNumber.isPresent()) {
                throw new IllegalArgumentException("This phone number is already used");
            }
            if (existingEmail.isPresent()) {
                throw new IllegalArgumentException("This e-mail is already used");
            }
            passengerRepository.save(passenger);
        }

        else {
            throw new IllegalArgumentException("Invalid passenger data");
        }
    }

    @Override
    public Passenger updatePassenger(final Integer id, final Passenger passenger) {
       Passenger currPassenger = getPassenger(id);
       PASSENGER_MAPPER.fill(passenger, currPassenger);
       return passengerRepository.save(currPassenger);
    }

    @Override
    public void deletePassenger(Integer id) {
        Passenger airportDelete = getPassenger(id);
        if(airportDelete != null){
            passengerRepository.delete(airportDelete);
            System.out.println("ID" + id + " is deleted");
        }
        else{
            throw new EntityNotFoundException(id + "Passenger is not found" );
        }
    }

    @Override
    public List<Passenger> getPassengersNameStartsWith(String prefix) {
        List<Passenger> passengers = passengerRepository.findByFirstnameStartingWith(prefix);
        if (passengers.isEmpty()) {
            throw new NotFoundException("No passengers found with the given prefix");
        }
        return passengers;
    }

    @Override
    public List<Passenger> getPassengersSortedViaLastNameAsUpperCase() {
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream().sorted(Comparator.comparing(Passenger::getLastname))
                .peek(p->p.setLastname(p.getLastname().toUpperCase()))
                .collect(Collectors.toList());
    }
}


