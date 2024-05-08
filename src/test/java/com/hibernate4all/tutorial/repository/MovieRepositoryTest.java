package com.hibernate4all.tutorial.repository;

import com.hibernate4all.tutorial.config.PersistenceConfig;
import com.hibernate4all.tutorial.domain.Movie;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class MovieRepositoryTest {

    /*
    @Autowired
    private MovieRepository movieRepository;

    @Test
    @Order(1)
    public void persist_nominalCase() {
        Movie movie = getNewMovie("Inception");
        movieRepository.persist(movie);
    }

    @Test
    @Order(2)
    public void findAll_nominalCase() {
        Movie movie1 = getNewMovie("Inception");
        Movie movie2 = getNewMovie("Black");
        movieRepository.persist(movie1);
        movieRepository.persist(movie2);
        List<Movie> movies = movieRepository.findAll();
        movies.stream()
                .findAny()
                .ifPresentOrElse((m) -> System.out.println(String.format("Id: %s, Name : %s", m.getId(), m.getName())),
                                 () -> System.out.println("No movies found"));
    }

    private Movie getNewMovie(String name) {
        Movie movie = new Movie();
        movie.setName(name);
        return movie;
    }*/

}
