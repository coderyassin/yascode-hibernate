package com.hibernate4all.tutorial.service;

import com.hibernate4all.tutorial.domain.Director;
import com.hibernate4all.tutorial.repository.jpa.projections.DirectorProjection;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface DirectorService {

    Director saveDirector(Director director);
    List<Director> findDirectorsByFirstName(String firstName);
    List<Director> findDirectorsByLastName(String lastName);
    List<Director> directorsByMovieTitle(String title, String directorFirstName, String directorLastName);
    List<Director> findByMovieTitle(String title, String directorFirstName, String directorLastName);
    List<Director> findDirectorsByFirstNameAndLastName(String firstName, String lastName);
    List<Director> findAllDirectorsWithConjunction();
    Map<String, Object> directors(int pageNumber, int pageSize);

    Page<DirectorProjection> getFirstNameAndLastName();
}
