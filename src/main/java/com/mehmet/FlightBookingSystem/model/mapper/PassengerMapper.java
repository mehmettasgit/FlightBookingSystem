package com.mehmet.FlightBookingSystem.model.mapper;

import com.mehmet.FlightBookingSystem.model.dto.PassengerDTO;
import com.mehmet.FlightBookingSystem.model.entity.Passenger;
import org.mapstruct.*;

@Mapper
public interface PassengerMapper {

    PassengerDTO toDto(Passenger entity);

    Passenger toEntity(PassengerDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void fill(Passenger source, @MappingTarget Passenger target);
}
