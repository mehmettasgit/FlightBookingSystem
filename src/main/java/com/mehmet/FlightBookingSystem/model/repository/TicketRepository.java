package com.mehmet.FlightBookingSystem.model.repository;

import com.mehmet.FlightBookingSystem.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {


}
