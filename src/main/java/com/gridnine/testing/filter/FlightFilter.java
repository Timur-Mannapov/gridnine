package com.gridnine.testing.filter;

import com.gridnine.testing.beans.Flight;

import java.util.List;

public interface FlightFilter {
    List<Flight> filter(List<Flight> flights);
}
