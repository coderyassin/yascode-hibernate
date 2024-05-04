package com.hibernate4all.tutorial.controller;

import com.hibernate4all.tutorial.domain.Movie;
import com.hibernate4all.tutorial.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/movies")
public class MovieController {

    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = {"/moviesByTitle/{search}", "/moviesByTitle"})
    public List<Movie> moviesByTitle(@PathVariable(value = "search", required = false) String search) {
        return movieService.findMoviesByTitle(Optional.ofNullable(search).orElse(""));
    }

    @GetMapping(value = {"/moviesByReleaseDate/{date}", "/moviesByReleaseDate"})
    public List<Movie> moviesThatReleaseDateGreaterThan(@PathVariable(value = "date", required = false) String date) {
        return movieService.findMoviesThatReleaseDateGreaterThan(Objects.isNull(date) ? LocalDate.now() : LocalDate.parse(date));
    }

    @GetMapping(value = {"/moviesByTitleAndReleaseDate/{title}/{date}", "/moviesByTitleAndReleaseDate"})
    public List<Movie> moviesByTitleAndReleaseDate(@PathVariable("title") String title, @PathVariable("date") LocalDate date) {
        return movieService.findMoviesByTitleAndReleaseDate(Optional.ofNullable(title).orElse(""),
                Optional.ofNullable(date).orElse(LocalDate.now()));
    }
}
