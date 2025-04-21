package com.gridnine.testing.filter.impl;

import com.gridnine.testing.beans.Flight;
import com.gridnine.testing.beans.builders.FlightBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TotalTimeSpentOnEarthFilterTest {

    private static final int NO_FLIGHTS = 0;
    private static final int ONE_FLIGHT = 1;

    @Test
    void testFilterTotalTimeSpentOnEarth_emptyList() {
        TotalTimeSpentOnEarthFilter filter = new TotalTimeSpentOnEarthFilter();
        List<Flight> filteredFlights = filter.filter(List.of());

        assertEquals(NO_FLIGHTS, filteredFlights.size());
    }

    @Test
    void testFilterNFlightsExceededTwoHours() {
        List<Flight> flights = FlightBuilder.createFlights();
        TotalTimeSpentOnEarthFilter filter = new TotalTimeSpentOnEarthFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertEquals(flights.size() - 2, filteredFlights.size());
    }

    @Test
    void testFilterOneFlightExceededTwoHours() {
        LocalDateTime now = LocalDateTime.now();
        Flight flight1 = FlightBuilder.createFlight(now, now.plusHours(1), now.plusHours(2), now.plusHours(3));
        Flight flight2 = FlightBuilder.createFlight(now, now.plusHours(1), now.plusHours(4), now.plusHours(5));
        List<Flight> flights = List.of(flight1, flight2);
        TotalTimeSpentOnEarthFilter filter = new TotalTimeSpentOnEarthFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertEquals(ONE_FLIGHT, filteredFlights.size());
        assertTrue(filteredFlights.contains(flight1));
    }

    @Test
    void testFilterTimeOnEarthEqualsTwoHours() {
        LocalDateTime now = LocalDateTime.now();
        Flight flight1 = FlightBuilder.createFlight(now, now.plusHours(1), now.plusHours(3), now.plusHours(4));
        List<Flight> flights = List.of(flight1);
        TotalTimeSpentOnEarthFilter filter = new TotalTimeSpentOnEarthFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertEquals(ONE_FLIGHT, filteredFlights.size());
        assertTrue(filteredFlights.contains(flight1));
    }
}