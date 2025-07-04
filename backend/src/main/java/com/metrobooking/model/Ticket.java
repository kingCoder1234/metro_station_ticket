package com.metrobooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Ticket {

    @Id
    private String ticketId;

    private String startStation;
    private String endStation;

    private LocalDateTime createdTime;
    private LocalDateTime expiryTime;

    private int usageCount = 0;
    private boolean entryUsed = false;
    private boolean exitUsed = false;

    private int price;

    @PrePersist
    public void prePersist() {
        this.ticketId = UUID.randomUUID().toString();
        this.createdTime = LocalDateTime.now();
        this.expiryTime = createdTime.plusHours(18);
    }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    public String getStartStation() { return startStation; }
    public void setStartStation(String startStation) { this.startStation = startStation; }

    public String getEndStation() { return endStation; }
    public void setEndStation(String endStation) { this.endStation = endStation; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getExpiryTime() { return expiryTime; }
    public void setExpiryTime(LocalDateTime expiryTime) { this.expiryTime = expiryTime; }

    public int getUsageCount() { return usageCount; }
    public void setUsageCount(int usageCount) { this.usageCount = usageCount; }

    public boolean isEntryUsed() { return entryUsed; }
    public void setEntryUsed(boolean entryUsed) { this.entryUsed = entryUsed; }

    public boolean isExitUsed() { return exitUsed; }
    public void setExitUsed(boolean exitUsed) { this.exitUsed = exitUsed; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
