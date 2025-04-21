package com.gridnine.testing.filter.impl;

import com.gridnine.testing.beans.Flight;
import com.gridnine.testing.filter.FlightFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightBeforeCurrentMomentFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))
                        ).collect(Collectors.toList());
    }
}
