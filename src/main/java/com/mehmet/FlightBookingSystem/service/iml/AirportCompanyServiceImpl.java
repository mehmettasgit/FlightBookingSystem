package com.mehmet.FlightBookingSystem.service.iml;

import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.AirportCompany;
import com.mehmet.FlightBookingSystem.model.entity.Flight;
import com.mehmet.FlightBookingSystem.model.repository.AirportCompanyRepository;
import com.mehmet.FlightBookingSystem.service.AirportComapnyService;
import com.mehmet.FlightBookingSystem.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AirportCompanyServiceImpl implements AirportComapnyService {

    private final AirportCompanyRepository airportCompanyRepository;

    private final FlightService flightService;


    @Override
    public List<AirportCompany> getAllAirportCompanies() {
        List<AirportCompany> airportCompanies =airportCompanyRepository.findAll();
        if(airportCompanies.isEmpty()){
            throw new NotFoundException("There is no AirportCompany in Database");
        }
        return airportCompanies;
    }

    @Override
    public AirportCompany getAirportCompany(Integer airportCompanyId) {
        AirportCompany airportCompany = airportCompanyRepository.findById(airportCompanyId).orElse(null);
        if (airportCompany == null) {
            throw new NotFoundException(airportCompanyId + "Id is not in the Database");
        }
        return airportCompany;
    }

    @Override
    public void addAirportCompany(AirportCompany airportCompany) {
        //Eklenen hava alanı şirketin adını kontrol et.
        if(airportCompany != null){
            Optional<AirportCompany> existingCompany = airportCompanyRepository.findByName(airportCompany.getName());
            if (existingCompany.isPresent()) {
                throw new IllegalArgumentException("Airport company with the same name already exists");
            }else
            {
                airportCompanyRepository.save(airportCompany);
            }
        }
        else {
            throw new IllegalArgumentException("Invalid airport data");
        }
    }

    @Override
    public void updateAirportCompany(Integer airportCompanyId, AirportCompany updatedAirportCompany) {
        // Mevcut havaalanı şirketini al
        AirportCompany existingAirportCompany = getAirportCompany(airportCompanyId);

        // Yeni veriyle mevcut havaalanı şirketini güncelle
        existingAirportCompany.setName(updatedAirportCompany.getName());
        // Diğer alanları da güncelleyebilirsiniz

        // Güncellenmiş havaalanı şirketini kaydet
        airportCompanyRepository.save(existingAirportCompany);

        System.out.println("AirportCompany with ID " + airportCompanyId + " UPDATED. New name: " + existingAirportCompany.getName());
    }

    @Override
    public void deleteAirportCompany(Integer airportCompanyId) {
        AirportCompany airportToDelete = getAirportCompany(airportCompanyId);
        if(airportToDelete != null){
            airportCompanyRepository.delete(airportToDelete);

            System.out.println("ID" +airportCompanyId + "silindi.");
        }
        else{
            throw new EntityNotFoundException("Havalanı bulunamadı, ID:" + airportCompanyId);
        }
    }

    @Override
    public boolean addNewFlight(Integer airportCompanyId,Integer flightId ){
        AirportCompany airportCompany = getAirportCompany(airportCompanyId);
        Flight flight = new Flight();
        flight.setId(flightId); // Flight'ın id'sini belirt
        flight.setAirportCompany(airportCompany);
        flightService.addFlight(flight);
        return true;
    }
}
