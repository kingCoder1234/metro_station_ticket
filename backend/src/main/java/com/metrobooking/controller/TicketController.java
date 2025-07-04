package com.metrobooking.controller;

import com.metrobooking.model.Station;
import com.metrobooking.model.Ticket;
import com.metrobooking.service.TicketService;
import com.metrobooking.config.StationConfigLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private StationConfigLoader stationLoader;

    @PostMapping("/buy")
    public ResponseEntity<Ticket> buyTicket(@RequestParam String start, @RequestParam String end) {
        return ResponseEntity.ok(ticketService.buyTicket(start, end));
    }

    @PostMapping("/use")
    public ResponseEntity<String> useTicket(@RequestParam String ticketId, @RequestParam String type) {
        return ResponseEntity.ok(ticketService.useTicket(ticketId, type));
    }

    @GetMapping("/status")
    public ResponseEntity<String> checkStatus(@RequestParam String ticketId) {
        return ResponseEntity.ok(ticketService.checkStatus(ticketId));
    }

    @GetMapping("/stations")
    public ResponseEntity<Map<String, Station>> getStations() {
        return ResponseEntity.ok(stationLoader.getStations());
    }
}
