package com.mehmet.FlightBookingSystem.model.entity;

import com.mehmet.FlightBookingSystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "airport")
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{validation.messages.airport.name}")
    private String name;

    @NotBlank(message = "{validation.messages.airport.address}")
    private String address;

    @Transient // This data will not be stored in database.
    private List<Address> addresses;

    public List<Address> formatToAddressList() {
        List<Address> response = new ArrayList<>();
        if (address != null && !address.isEmpty()) {
            String[] splitedAddresses = address.split("//");
            Arrays.stream(splitedAddresses).forEach(split -> {
                String[] splited = split.trim().split("/");
                if (splited.length == 2) {
                    response.add(new Address(splited[1], splited[0]));
                }
            });
        }
        return response;
    }
}
