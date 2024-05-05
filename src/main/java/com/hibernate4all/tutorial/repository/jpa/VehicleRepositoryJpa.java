package com.hibernate4all.tutorial.repository.jpa;

import com.hibernate4all.tutorial.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VehicleRepositoryJpa extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {
}
