package com.mehmet.FlightBookingSystem.controller;


import com.mehmet.FlightBookingSystem.model.entity.Flight;
import com.mehmet.FlightBookingSystem.model.entity.Ticket;
import com.mehmet.FlightBookingSystem.service.FlightService;
import com.mehmet.FlightBookingSystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    private final FlightService flightService;

    @GetMapping(value = "/welcome")
    public String welcome(){
        return "Welcome to Ticket Service!";
    }

    @GetMapping(value = "/all")
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping(value = "/{id}")
    public Ticket getTicket(@PathVariable @Min(1) Integer id){
        return ticketService.getTicket(id);
    }

    @PostMapping(value = "/create")
    public void saveTicket(@Valid @RequestBody Ticket ticket){
        ticketService.addTicket(ticket);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Integer id) {
        boolean isDeleted = ticketService.deleteTicket(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all/flight/{id}")
    public List<Ticket> getFlightRelatedTickets(@PathVariable @Min(1) Integer id){
        Flight flight = flightService.getFlight(id);
        Page<Ticket> relatedFlightTickets = ticketService.getRelatedFlightTickets(Pageable.unpaged(), flight);
        return relatedFlightTickets.getContent();
    }
}
