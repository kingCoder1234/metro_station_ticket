package com.metrobooking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "station")
public class Station {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "start_station")
    private boolean startStation;

    @Column(name = "last_station")
    private boolean lastStation;

    @Column(name = "price")
    private int price;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isStartStation() { return startStation; }
    public void setStartStation(boolean startStation) { this.startStation = startStation; }

    public boolean isLastStation() { return lastStation; }
    public void setLastStation(boolean lastStation) { this.lastStation = lastStation; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
