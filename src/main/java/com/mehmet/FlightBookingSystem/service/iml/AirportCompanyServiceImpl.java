package com.mehmet.FlightBookingSystem.service.iml;

import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.Airport;
import com.mehmet.FlightBookingSystem.model.entity.AirportCompany;
import com.mehmet.FlightBookingSystem.model.mapper.repository.AirportCompanyRepository;
import com.mehmet.FlightBookingSystem.service.AirportComapnyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AirportCompanyServiceImpl implements AirportComapnyService {

    private final AirportCompanyRepository airportCompanyRepository;


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
            }
        }
        else {
            throw new IllegalArgumentException("Invalid airport data");
        }
    }

    @Override
    public void updateAirportCompany(Integer airportCompanyId, AirportCompany updatedAirportCompany) {
        getAirportCompany(airportCompanyId);
        updatedAirportCompany.setId(airportCompanyId);
        airportCompanyRepository.save(updatedAirportCompany);
    }

    @Override
    public void deleteAirportCompany(Integer airportCompanyId) {
        AirportCompany airportToDelete = getAirportCompany(airportCompanyId);
        if(airportToDelete != null){
            airportCompanyRepository.delete(airportToDelete);
        }
        else{
            throw new EntityNotFoundException("Havalanı bulunamadı, ID:" + airportCompanyId);
        }
    }
}
