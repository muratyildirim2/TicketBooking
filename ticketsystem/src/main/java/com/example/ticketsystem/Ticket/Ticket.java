package com.example.ticketsystem.Ticket;

import jakarta.persistence.*;

@Entity
@Table
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;
    @Column(name = "ticket_price")

    private Integer ticketPrice;
    @Column(name = "is_sold")
    private Boolean isSold;
    @Column(name = "ticket_number")
    private Integer ticketNumber;

    public Ticket(Integer ticketId, Integer ticketPrice, Boolean isSold, Integer ticketNumber) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.isSold = isSold;
        this.ticketNumber = ticketNumber;
    }

    public Ticket() {

    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
