package com.hibernate4all.tutorial.repository.jpa;

import com.hibernate4all.tutorial.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieRepositoryJpa extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
}
