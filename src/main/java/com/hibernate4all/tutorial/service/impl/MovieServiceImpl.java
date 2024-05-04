package com.hibernate4all.tutorial.service.impl;

import com.hibernate4all.tutorial.domain.Movie;
import com.hibernate4all.tutorial.repository.jpa.MovieRepositoryJpa;
import com.hibernate4all.tutorial.repository.jpa.specifications.MovieSpecifications;
import com.hibernate4all.tutorial.service.MovieService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepositoryJpa movieRepositoryJpa;
    private final com.hibernate4all.tutorial.repository.MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepositoryJpa movieRepositoryJpa,
                            com.hibernate4all.tutorial.repository.MovieRepository movieRepository) {
        this.movieRepositoryJpa = movieRepositoryJpa;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findMoviesByTitle(String search) {
        return movieRepositoryJpa.findAll(MovieSpecifications.nameContains(search));
    }

    @Override
    public List<Movie> findMoviesThatReleaseDateGreaterThan(LocalDate date) {
        return movieRepositoryJpa.findAll(MovieSpecifications.releaseDateGreaterThan(date));
    }

    @Override
    public List<Movie> findMoviesByTitleAndReleaseDate(String title, LocalDate date) {
        return movieRepositoryJpa.findAll(MovieSpecifications.nameContains(title).and(MovieSpecifications.releaseDateGreaterThan(date)));
    }

    @Override
    public void saveMovie(Movie movie) {
        movieRepository.persist(movie);
    }
}
