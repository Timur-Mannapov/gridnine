package com.gridnine.testing.filter.impl;

import com.gridnine.testing.beans.Flight;
import com.gridnine.testing.filter.FlightFilter;

import java.util.List;
import java.util.stream.Collectors;

public class SegmentsWithAnArrivalDateEarlierFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}
