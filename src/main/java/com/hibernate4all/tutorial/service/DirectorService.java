package com.hibernate4all.tutorial.service;

import com.hibernate4all.tutorial.domain.Director;

import java.util.List;

public interface DirectorService {

    Director saveDirector(Director director);
    List<Director> directorsByMovieTitle(String title, String directorName);

}
