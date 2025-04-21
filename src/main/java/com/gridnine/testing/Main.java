package com.gridnine.testing;

import com.gridnine.testing.beans.Flight;
import com.gridnine.testing.beans.builders.FlightBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.impl.FlightBeforeCurrentMomentFilter;
import com.gridnine.testing.filter.impl.SegmentsWithAnArrivalDateEarlierFilter;
import com.gridnine.testing.filter.impl.TotalTimeSpentOnEarthFilter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        printFlights("1. Вылет до текущего момента времени:", new FlightBeforeCurrentMomentFilter(), flights);

        printFlights("2. Сегменты с датой прилёта раньше даты вылета:", new SegmentsWithAnArrivalDateEarlierFilter(), flights);

        printFlights("3. Общее время, проведённое на земле превышает два часа:", new TotalTimeSpentOnEarthFilter(), flights);
    }

    private static void printFlights(String title, FlightFilter filter, List<Flight> flights) {
        System.out.println(title);
        List<Flight> filteredFlights = filter.filter(flights);
        if (filteredFlights.isEmpty()) {
            System.out.println("\tНет перелетов, соответствующих условиям.");
        } else {
            filteredFlights.forEach(flight -> System.out.println("\t" + flight));
        }
        System.out.println("\tКоличество отфильтрованных перелетов: " + filteredFlights.size());
        System.out.println();
    }
}