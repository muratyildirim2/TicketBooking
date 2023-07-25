package com.example.ticketsystem.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("getbyticketid/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getbyticketnumber/{ticketNumber}")
    public ResponseEntity<Ticket> getTicketByTicketNumber(@PathVariable Integer ticketNumber) {
        Ticket ticket = ticketService.getTicketByTicketNumber(ticketNumber);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket savedTicket = ticketService.saveTicket(ticket);
        return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
    }

    @DeleteMapping("deletebyticketnumber/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/buybyticketnumber/{ticketNumber}")
    public ResponseEntity<Ticket> updateTicketStatusBuy(@PathVariable Integer ticketNumber, @RequestBody Ticket updatedTicket) {
        updatedTicket.setSold(true);
        Ticket ticketByNumber = ticketService.getTicketByTicketNumber(updatedTicket.getTicketNumber());
        ticketByNumber.setSold(true);
        Ticket ticket = ticketService.saveTicket(ticketByNumber);

        return ResponseEntity.ok(ticket);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/refundbyticketnumber/{ticketNumber}")
    public ResponseEntity<Ticket> updateTicketStatusRefund(@PathVariable Integer ticketNumber, @RequestBody Ticket updatedTicket) {
        updatedTicket.setSold(false);
        Ticket ticketByNumber = ticketService.getTicketByTicketNumber(updatedTicket.getTicketNumber());
        ticketByNumber.setSold(false);
        Ticket ticket = ticketService.saveTicket(ticketByNumber);

        return ResponseEntity.ok(ticket);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/notsoldtickets")
    public ResponseEntity<List<Ticket>> getNotSoldTickets() {
        List<Ticket> notSoldTickets = ticketService.findBySoldFalse();
        return ResponseEntity.ok(notSoldTickets);
    }

    @GetMapping("/soldtickets")
    public ResponseEntity<List<Ticket>> getSoldTickets() {
        List<Ticket> soldTickets = ticketService.getSoldTickets();
        return new ResponseEntity<>(soldTickets, HttpStatus.OK);
    }


}
