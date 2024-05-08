package com.hibernate4all.tutorial.repository;

import com.hibernate4all.tutorial.domain.Director;
import com.hibernate4all.tutorial.repository.jpa.DirectorRepositoryJpa;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DirectorRepositoryTests {

    @Autowired
    private DirectorRepositoryJpa directorRepositoryJpa;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveDirectorTest() {

        Director director = Director.builder()
                .firstName("Nour-Eddine")
                .lastName("Lakhmari")
                .build();

        directorRepositoryJpa.save(director);

        assertThat(director.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void getDirectorTest(){

        Director director = directorRepositoryJpa.findById(1L).get();

        assertThat(director.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfDirectorsTest(){

        List<Director> directors = directorRepositoryJpa.findAll();

        assertThat(directors.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateDirectorTest(){

        Director director = directorRepositoryJpa.findById(1L).get();

        director.setFirstName("Yassin");

        Director directorSaved = directorRepositoryJpa.save(director);

        assertThat(directorSaved.getFirstName()).isEqualTo("Yassin");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteDirectorTest(){

        Director director = directorRepositoryJpa.findById(1L).get();

        directorRepositoryJpa.delete(director);

        Director directorT = null;

        Optional<Director> optionalDirector = directorRepositoryJpa.findById(1L);

        if(optionalDirector.isPresent()){
            directorT = optionalDirector.get();
        }

        assertThat(directorT).isNull();
    }
}
