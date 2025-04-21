package com.gridnine.testing.filter.impl;

import com.gridnine.testing.beans.Flight;
import com.gridnine.testing.beans.builders.FlightBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightBeforeCurrentMomentFilterTest {

    private static final int NO_FLIGHT = 0;
    private static final int ONE_FLIGHT = 1;

    @Test
    void testFilterFlightEmptyList() {
        FlightBeforeCurrentMomentFilter filter = new FlightBeforeCurrentMomentFilter();
        List<Flight> filteredFlights = filter.filter(List.of());
        assertEquals(NO_FLIGHT, filteredFlights.size());
    }

    @Test
    void testFilterFlightsOneFlightInPast() {
        LocalDateTime now = LocalDateTime.now();
        Flight pastFlight = FlightBuilder.createFlight(now.minusHours(2), now.minusHours(1));
        Flight futureFlight = FlightBuilder.createFlight(now.plusHours(1), now.plusHours(2));
        List<Flight> flights = List.of(pastFlight, futureFlight);

        FlightBeforeCurrentMomentFilter filter = new FlightBeforeCurrentMomentFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertEquals(ONE_FLIGHT, filteredFlights.size());
        assertTrue(filteredFlights.contains(futureFlight));
    }

    @Test
    void testFilterAllFlightsInPast() {
        LocalDateTime now = LocalDateTime.now();
        Flight pastFlight1 = FlightBuilder.createFlight(now.minusHours(2), now.minusHours(1));
        Flight pastFlight2 = FlightBuilder.createFlight(now.minusDays(1), now.minusDays(1).plusHours(1));
        List<Flight> flights = List.of(pastFlight1, pastFlight2);

        FlightBeforeCurrentMomentFilter filter = new FlightBeforeCurrentMomentFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertEquals(NO_FLIGHT, filteredFlights.size());
    }
}
