package com.hibernate4all.tutorial.repository.jpa.specifications;

import com.hibernate4all.tutorial.domain.Vehicle;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class VehicleSpecifications {

    private VehicleSpecifications(){}

    /**
     *
     * @param maybeParam : the value of the filter
     * @param entityPropertyName : the name of the property of the entity we want to match
     * @return
     */
    public static Specification<Vehicle> integerEquals(Optional<Integer> maybeParam, String entityPropertyName) {
        return (root, query, criteriaBuilder) -> maybeParam
                .map(param -> criteriaBuilder.equal(root.get(entityPropertyName), param))
                .orElseGet(criteriaBuilder::conjunction);
    }

    public static Specification<Vehicle> stringContained(Optional<String> maybeParam, String entityPropertyName) {
        return (root, query, criteriaBuilder) -> maybeParam
                .map(param -> criteriaBuilder.like(criteriaBuilder.lower(root.get(entityPropertyName)), "%" + param.toLowerCase() + "%"))
                .orElseGet(criteriaBuilder::conjunction);
    }

    public static Specification<Vehicle> queryWithFilters(Optional<String> type, Optional <String> brand, Optional <String> model, Optional <Integer> horsepower) {
        return Specification
                .where(VehicleSpecifications.stringContained(type, "type"))
                .and(VehicleSpecifications.stringContained(brand, "brand"))
                .and(VehicleSpecifications.stringContained(model, "model"))
                .and(VehicleSpecifications.integerEquals(horsepower, "horsepower"));
    }
}
