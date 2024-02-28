package com.mehmet.FlightBookingSystem.model.dto;

import com.mehmet.FlightBookingSystem.model.Address;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.StringJoiner;


@Data
public class AirportDTO {

    @NotBlank
    private String name;

    @NotEmpty
    private List<@Valid Address> address;

    public String formatAdresses(){
        StringJoiner strJoiner = new StringJoiner(" // ");
        this.address.forEach(address -> {
            strJoiner.add(address.addressDBFormat());
                }
        );
        return strJoiner.toString();
    }
}
