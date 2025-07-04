package com.metrobooking.service;

import com.metrobooking.config.StationConfigLoader;
import com.metrobooking.model.Station;
import com.metrobooking.model.Ticket;
import com.metrobooking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private StationConfigLoader stationLoader;

    public Ticket buyTicket(String start, String end) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(UUID.randomUUID().toString());
        ticket.setStartStation(start);
        ticket.setEndStation(end);
        ticket.setEntryUsed(false);
        ticket.setExitUsed(false);
        ticket.setUsageCount(0);
        ticket.setExpiryTime(LocalDateTime.now().plusHours(18));

        Map<String, Station> stations = stationLoader.getStations();
        Station startStation = stations.get(start);
        Station endStation = stations.get(end);
        int price = Math.abs(startStation.getPrice() - endStation.getPrice());
        ticket.setPrice(price);

        return ticketRepository.save(ticket);
    }

    public String useTicket(String ticketId, String type) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isEmpty()) {
            return "Invalid ticket ID.";
        }

        Ticket ticket = optionalTicket.get();

        if (ticket.getExpiryTime().isBefore(LocalDateTime.now())) {
            return "Ticket expired.";
        }

        switch (type.toLowerCase()) {
            case "entry":
                if (ticket.isEntryUsed()) return "Entry already used.";
                ticket.setEntryUsed(true);
                break;

            case "exit":
                if (!ticket.isEntryUsed()) return "Cannot use exit before entry.";
                if (ticket.isExitUsed()) return "Exit already used.";
                ticket.setExitUsed(true);
                break;

            default:
                return "Invalid type. Use 'entry' or 'exit'.";
        }

        ticket.setUsageCount(ticket.getUsageCount() + 1);
        ticketRepository.save(ticket);

        return "Ticket used successfully.";
    }

    public String checkStatus(String ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isEmpty()) {
            return "Ticket not found.";
        }

        Ticket ticket = optionalTicket.get();

        return String.format(
            "Ticket from %s to %s. Entry used: %b, Exit used: %b, Usage count: %d, Expires: %s, Price: ₹%d",
            ticket.getStartStation(),
            ticket.getEndStation(),
            ticket.isEntryUsed(),
            ticket.isExitUsed(),
            ticket.getUsageCount(),
            ticket.getExpiryTime(),
            ticket.getPrice()
        );
    }
}
