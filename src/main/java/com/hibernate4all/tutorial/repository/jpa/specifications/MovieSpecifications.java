package com.hibernate4all.tutorial.repository.jpa.specifications;

import com.hibernate4all.tutorial.domain.Movie;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class MovieSpecifications {

    public static Specification<Movie> nameContains(String search) {
        return  (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), String.format("%%%s%%", search));
    }

    public static Specification<Movie> releaseDateGreaterThan(LocalDate date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("releaseDate"), date);
    }

}
