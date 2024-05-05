package com.hibernate4all.tutorial.service.impl;

import com.hibernate4all.tutorial.domain.Vehicle;
import com.hibernate4all.tutorial.repository.jpa.VehicleRepositoryJpa;
import com.hibernate4all.tutorial.repository.jpa.specifications.VehicleSpecifications;
import com.hibernate4all.tutorial.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepositoryJpa vehicleRepositoryJpa;

    public VehicleServiceImpl(VehicleRepositoryJpa vehicleRepositoryJpa) {
        this.vehicleRepositoryJpa = vehicleRepositoryJpa;
    }

    @Override
    public List<Vehicle> getFilteredVehicles(Optional<String> type, Optional<String> brand, Optional<String> model, Optional<Integer> horsepower) {
        return vehicleRepositoryJpa.findAll(VehicleSpecifications.queryWithFilters(type, brand, model, horsepower));
    }
}
