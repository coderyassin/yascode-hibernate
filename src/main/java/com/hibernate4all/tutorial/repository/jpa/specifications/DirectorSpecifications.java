package com.hibernate4all.tutorial.repository.jpa.specifications;

import com.hibernate4all.tutorial.domain.Director;
import org.springframework.data.jpa.domain.Specification;

public class DirectorSpecifications {

    public static Specification<Director> findDirectorByMovieTitle(String title, String directorFirstName, String directorLastName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(criteriaBuilder.like(root.join("movies").get("name"), "%" + title + "%"),
                criteriaBuilder.like(root.get("firstName"), "%" + directorFirstName + "%"),
                criteriaBuilder.like(root.get("lastName"), "%" + directorLastName + "%"));
    }

    public static Specification<Director> findAllDirectors() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

}
