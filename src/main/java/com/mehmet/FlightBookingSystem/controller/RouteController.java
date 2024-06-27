package com.mehmet.FlightBookingSystem.controller;

import com.mehmet.FlightBookingSystem.model.entity.Route;
import com.mehmet.FlightBookingSystem.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/route")
public class RouteController {

    private final RouteService routeService;

    @GetMapping
    public String welcome(){
        return "Welcome to Route service";
    }

  /*  @GetMapping(value = "/all")
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }*/

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllRoutes(){
        List<Route> routes = routeService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public Route getRoute(@PathVariable @Min(1) Integer id){
        return routeService.getRoute(id);
    }

    @PostMapping(value = "/create")
    public void saveRoute(@Valid @RequestBody Route route){
        routeService.addRoute(route);
    }

    @PutMapping(value = "/updateroute/{routeID}")
    public ResponseEntity<Void> updateRoute(@PathVariable Integer routeID,
                                              @RequestBody @Valid Route updatedRoute)
    {
        routeService.updateRoute(routeID, updatedRoute);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{routeid}")
   public boolean deleteRoute(@PathVariable Integer routeid){
        return routeService.deleteRoute(routeid);
   }

   public ResponseEntity<Route> getOneByDepartureIdV2(@PathVariable @Min(1) Integer depId){
        Route route = routeService.getFirstRouteByDepartureAirportByDefault(depId);
        return new ResponseEntity<>(route, HttpStatus.OK);

    }
}
