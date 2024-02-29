package com.mehmet.FlightBookingSystem.service.iml;

import com.mehmet.FlightBookingSystem.exception.NotFoundException;
import com.mehmet.FlightBookingSystem.model.entity.Route;
import com.mehmet.FlightBookingSystem.model.repository.RouteRepository;
import com.mehmet.FlightBookingSystem.service.AirportService;
import com.mehmet.FlightBookingSystem.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    private final AirportService airportService;


    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRoute(Integer id) {
        Optional<Route> byId = routeRepository.findById(id);
        return byId.orElseThrow(()-> new NotFoundException("Route"));
    }

    @Override
    public void addRoute(Route route) {
        routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Route route) {
        return null;
    }

    @Override
    public boolean deleteRoute(Integer id) {
        routeRepository.delete(getRoute(id));
        return true;
    }

    @Override
    public Route getFirstRouteByDepartureAirportByDefault(Integer departureAirportId) {
        return null;
    }


}
