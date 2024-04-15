package com.mehmet.FlightBookingSystem.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "code can not be null")
    private String code;

    @NotNull(message = "quota can not be null")
    private Integer quota;

    @NotNull(message = "price can not be null")
    private Integer price;

    @NotNull(message = "departure date can not be null")
    @Column(name = "departure_date")
    private Date departureDate;

    @NotNull(message = "estimated arrival date can not be null")
    @Column(name = "estimated_arrival_date")
    private Date estimatedArrivalDate;

    @NotNull(message = "airport can not be null")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "airport_company_id", referencedColumnName = "id")
    private AirportCompany airportCompany;

}
