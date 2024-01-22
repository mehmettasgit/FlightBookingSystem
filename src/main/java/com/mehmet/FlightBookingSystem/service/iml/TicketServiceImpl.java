package com.mehmet.FlightBookingSystem.service.iml;

import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.Flight;
import com.mehmet.FlightBookingSystem.model.entity.Ticket;
import com.mehmet.FlightBookingSystem.model.mapper.repository.TicketRepository;
import com.mehmet.FlightBookingSystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicket(Integer id) {
        Optional<Ticket> byId = ticketRepository.findById(id);
        return byId.orElseThrow(()-> new NotFoundException("Ticket"));
    }

    @Override
    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public boolean deleteTicket(Integer id) {
       Ticket ticket = getTicket(id);
       ticketRepository.delete(ticket);
       return true;
    }

    @Override
    public Page<Ticket> getRelatedFlightTickets(Pageable pageable, Flight flight) {
        return null;
    }
}
