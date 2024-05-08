package com.hibernate4all.tutorial.repository;

import com.hibernate4all.tutorial.domain.Director;
import com.hibernate4all.tutorial.repository.jpa.DirectorRepositoryJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DirectorRepositoryTest {

    @Autowired
    private DirectorRepositoryJpa directorRepositoryJpa;
    private Director director;

    @BeforeEach
    public void setUp() {
        director = Director.builder()
                .firstName("Nour-Eddine")
                .lastName("Lakhmari")
                .build();
    }

    @DisplayName("JUnit test for save director operation")
    @Test
    public void givenDirectorObject_whenSave_thenReturnSavedDirector(){

        Director director = Director.builder()
                .firstName("Mahmoud")
                .lastName("Frites")
                .build();

        Director directorSaved = directorRepositoryJpa.save(director);

        assertThat(directorSaved).isNotNull();
        assertThat(directorSaved.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for get all directors operation")
    @Test
    public void givenDirectorsList_whenFindAll_thenDirectorsList(){

        Director director1 = Director.builder()
                .firstName("James")
                .lastName("Cameron")
                .build();

        directorRepositoryJpa.save(director);
        directorRepositoryJpa.save(director1);

        // when -  action or the behaviour that we are going test
        List<Director> directors = directorRepositoryJpa.findAll();

        // then - verify the output
        assertThat(directors).isNotNull();
        assertThat(directors.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for get director by id operation")
    @Test
    public void givenDirectorsObject_whenFindById_thenReturnDirectorsObject(){

        directorRepositoryJpa.save(director);

        // when -  action or the behaviour that we are going test
        Director directorDB = directorRepositoryJpa.findById(director.getId()).get();

        // then - verify the output
        assertThat(directorDB).isNotNull();
    }

    @DisplayName("JUnit test for update director operation")
    @Test
    public void givenDirectorObject_whenUpdateDirector_thenReturnUpdatedDirector(){

        directorRepositoryJpa.save(director);

        // when -  action or the behaviour that we are going test
        Director directorSaved = directorRepositoryJpa.findById(director.getId()).get();
        directorSaved.setFirstName("Yassin");
        directorSaved.setLastName("MELLOUKI");
        Director updatedDirector = directorRepositoryJpa.save(directorSaved);

        // then - verify the output
        assertThat(updatedDirector.getFirstName()).isEqualTo("Yassin");
        assertThat(updatedDirector.getLastName()).isEqualTo("MELLOUKI");
    }

    @DisplayName("JUnit test for delete director operation")
    @Test
    public void givenDirectorObject_whenDelete_thenRemoveDirector(){

        directorRepositoryJpa.save(director);

        // when -  action or the behaviour that we are going test
        directorRepositoryJpa.deleteById(director.getId());
        Optional<Director> directorOptional = directorRepositoryJpa.findById(director.getId());

        // then - verify the output
        assertThat(directorOptional).isEmpty();
    }

}
