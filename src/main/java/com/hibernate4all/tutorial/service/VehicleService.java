package com.hibernate4all.tutorial.service;

import com.hibernate4all.tutorial.domain.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> getFilteredVehicles(Optional<String> type, Optional <String> brand, Optional <String> model, Optional <Integer> horsepower);

}
