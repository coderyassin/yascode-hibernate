package com.hibernate4all.tutorial.service;

import com.hibernate4all.tutorial.domain.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {

    List<Movie> findMoviesByTitle(String search);

    List<Movie> findMoviesThatReleaseDateGreaterThan(LocalDate date);

    List<Movie> findMoviesByTitleAndReleaseDate(String title, LocalDate date);
    void saveMovie(Movie movie);

}
