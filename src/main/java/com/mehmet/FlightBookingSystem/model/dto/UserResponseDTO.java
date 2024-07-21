package com.mehmet.FlightBookingSystem.model.dto;

import com.mehmet.FlightBookingSystem.model.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    List<Role> roles;

}
