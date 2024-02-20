package com.mehmet.FlightBookingSystem.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passenger")
public class Passenger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "firstname can not be null")
    private String firstname;

    @NotBlank(message = "lastname can not be null")
    private String lastname;

    private String gender;

    private Integer age;

    private String phone;

    @Email
    @NotBlank
    private String email;
}
