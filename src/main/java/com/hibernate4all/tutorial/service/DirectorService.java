package com.hibernate4all.tutorial.service;

import com.hibernate4all.tutorial.domain.Director;

import java.util.List;

public interface DirectorService {

    Director saveDirector(Director director);
    List<Director> findDirectorsByFirstName(String firstName);
    List<Director> findDirectorsByLastName(String lastName);
    List<Director> directorsByMovieTitle(String title, String directorFirstName, String directorLastName);
    List<Director> findByMovieTitle(String title, String directorFirstName, String directorLastName);
    List<Director> findDirectorsByFirstNameAndLastName(String firstName, String lastName);

    List<Director> findAllDirectorsWithConjunction();
}
