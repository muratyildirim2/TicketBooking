package com.example.ticketsystem.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket getTicketByTicketNumber(Integer ticketNumber) {
        return ticketRepository.findByTicketNumber(ticketNumber);
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> findBySoldFalse() {
        List<Ticket> allTickets = ticketRepository.findAll();
        List<Ticket> notSoldTickets = new java.util.ArrayList<>(Collections.emptyList());
        for (Ticket ticket : allTickets) {
            if(!ticket.getSold()) {
                notSoldTickets.add(ticket);
            }
        }
        return notSoldTickets;
    }
    public List<Ticket> getSoldTickets() {
        return ticketRepository.findByIsSoldTrue();
    }

}
