package com.gridnine.testing.filter.impl;

import com.gridnine.testing.beans.Flight;
import com.gridnine.testing.beans.Segment;
import com.gridnine.testing.filter.FlightFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TotalTimeSpentOnEarthFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> calculateTotalEarthTime(flight.getSegments()) <= 2)
                .collect(Collectors.toList());
    }

    private int calculateTotalEarthTime(List<Segment> segments) {
        if (segments.size() <= 1) {
            return 0;
        }
        int totalTimeOnEarth = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime arrivalDate = segments.get(i).getArrivalDate();
            LocalDateTime departureDate = segments.get(i + 1).getDepartureDate();
            totalTimeOnEarth += departureDate.getHour() - arrivalDate.getHour();

        }
        return totalTimeOnEarth;
    }
}
