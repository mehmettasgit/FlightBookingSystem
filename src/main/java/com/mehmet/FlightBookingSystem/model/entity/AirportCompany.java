package com.mehmet.FlightBookingSystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="airport_company")
public class AirportCompany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "name can not be null")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "airportCompany", cascade = CascadeType.MERGE)
    private List<Flight> flights;

    @Override
    public String toString() {
        return "AirportCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
