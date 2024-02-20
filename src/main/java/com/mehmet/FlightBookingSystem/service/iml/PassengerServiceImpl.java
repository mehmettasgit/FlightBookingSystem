package com.mehmet.FlightBookingSystem.service.iml;
import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.Passenger;
import com.mehmet.FlightBookingSystem.model.mapper.repository.PassengerRepository;
import com.mehmet.FlightBookingSystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
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
        if( passenger != null){
            Optional<Passenger> existingEmail = passengerRepository.findByEmail(passenger.getEmail());
            Optional<Passenger> existingPhoneNumber = passengerRepository.findByPhone(passenger.getPhone());
            if (existingPhoneNumber.isPresent()) {
                throw new IllegalArgumentException("This phone number is already used");
            }

            if (existingEmail.isPresent()) {
                throw new IllegalArgumentException("This e-mail is already used");
            }
        }
        else {
            throw new IllegalArgumentException("Invalid passenger data");
        }
    }

    @Override
    public void updatePassenger(Integer id, Passenger passenger) {
        Passenger existingpassenger = getPassenger(id);
        if(existingpassenger !=null) {
            // Var olan yolcu bulunduğunda, güncelleme işlemlerini gerçekleştirin
            existingpassenger.setFirstname(passenger.getFirstname());
            existingpassenger.setLastname(passenger.getLastname());
            existingpassenger.setGender(passenger.getGender());
            existingpassenger.setAge(passenger.getAge());
            existingpassenger.setPhone(passenger.getPhone());
            existingpassenger.setEmail(passenger.getEmail());

            // Veritabanında güncelleme yapın
            passengerRepository.save(existingpassenger);
            System.out.println("Passenger with ID " + id + " has been updated.");
        }
        else {
            throw new NotFoundException("Passenger with ID " + id + " not found.");
        }
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
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream().filter(p->p.getFirstname().startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public List<Passenger> getPassengersSortedViaLastNameAsUpperCase() {
        return null;
    }


}
