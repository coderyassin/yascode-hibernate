package com.hibernate4all.tutorial.controller;

import com.hibernate4all.tutorial.domain.Vehicle;
import com.hibernate4all.tutorial.service.VehicleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/list")
    public List<Vehicle> getVehiclesList(
            @RequestParam(name = "type", required = false) Optional<String> type,
            @RequestParam(name = "brand",required = false) Optional<String> brand,
            @RequestParam(name = "model", required = false) Optional<String> model,
            @RequestParam(name = "horsepower", required = false) Optional<Integer> horsepower) {
        return vehicleService.getFilteredVehicles(type, brand, model, horsepower);
    }

}
