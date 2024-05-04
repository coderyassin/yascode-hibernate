package com.hibernate4all.tutorial.repository.jpa.specifications;

import com.hibernate4all.tutorial.domain.Director;
import org.springframework.data.jpa.domain.Specification;

public class DirectorSpecifications {

    public static Specification<Director> findDirectorByMovieTitle(String title, String directorName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(criteriaBuilder.equal(root.join("movies").get("name"), title),
                criteriaBuilder.like(root.get("firstName"), "%" + directorName + "%"));
    }

}
