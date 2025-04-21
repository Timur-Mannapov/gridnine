package com.gridnine.testing.filter.impl;

import com.gridnine.testing.beans.Flight;
import com.gridnine.testing.beans.builders.FlightBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SegmentsWithAnArrivalDateEarlierFilterTest {

    private static final int NO_FLIGHTS = 0;
    private static final int ONE_FLIGHT = 1;

    @Test
    void testFilterSegmentsWithArrivalBeforeDeparture_emptyList() {
        SegmentsWithAnArrivalDateEarlierFilter filter = new SegmentsWithAnArrivalDateEarlierFilter();
        List<Flight> filteredFlights = filter.filter(List.of());
        assertEquals(NO_FLIGHTS, filteredFlights.size());
    }

    @Test
    void testFilterSegmentsWithArrivalBeforeDeparture_noInvalidSegments() {
        List<Flight> flights = FlightBuilder.createFlights();
        SegmentsWithAnArrivalDateEarlierFilter filter = new SegmentsWithAnArrivalDateEarlierFilter();
        List<Flight> filteredFlights = filter.filter(flights);
        assertEquals(flights.size() - 1, filteredFlights.size());
    }

    @Test
    void testFilterSegmentsWithArrivalBeforeDeparture_someInvalidSegments() {
        LocalDateTime now = LocalDateTime.now();
        Flight invalidFlight = FlightBuilder.createFlight(now.plusHours(1), now.minusHours(1));
        Flight validFlight = FlightBuilder.createFlight(now.plusHours(1), now.plusHours(2));
        List<Flight> flights = List.of(invalidFlight, validFlight);

        SegmentsWithAnArrivalDateEarlierFilter filter = new SegmentsWithAnArrivalDateEarlierFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertEquals(ONE_FLIGHT, filteredFlights.size());
        assertTrue(filteredFlights.contains(validFlight));
    }
}
