package com.mehmet.FlightBookingSystem.service;

import com.mehmet.FlightBookingSystem.model.entity.Route;

import java.util.List;

public interface RouteService {

    List<Route> getAllRoutes();

    Route getRoute(Integer id);

    void addRoute(Route route);

    void updateRoute(Integer id, Route route);

    boolean deleteRoute(Integer id);

    Route getFirstRouteByDepartureAirportByDefault(Integer departureAirportId);
}
