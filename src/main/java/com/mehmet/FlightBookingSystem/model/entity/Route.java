package com.mehmet.FlightBookingSystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "route")
@Entity
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Valid
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
    private Airport departureAirport;

    @Valid
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id")
    private Airport arrivalAirport;
}
