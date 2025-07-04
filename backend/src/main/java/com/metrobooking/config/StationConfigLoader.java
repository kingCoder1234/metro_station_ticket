package com.metrobooking.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metrobooking.model.Station;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class StationConfigLoader {

    private Map<String, Station> stationMap;

    @PostConstruct
    public void loadStationConfig() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new ClassPathResource("stations.json").getInputStream();
        JsonNode root = mapper.readTree(is).get("stations");

        stationMap = new HashMap<>();
        Iterator<String> fieldNames = root.fieldNames();
        while (fieldNames.hasNext()) {
            String name = fieldNames.next();
            Station station = mapper.treeToValue(root.get(name), Station.class);
            stationMap.put(name, station);
        }
    }

    public Map<String, Station> getStations() {
        return stationMap;
    }
}
