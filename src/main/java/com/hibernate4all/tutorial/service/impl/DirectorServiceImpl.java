package com.hibernate4all.tutorial.service.impl;

import com.hibernate4all.tutorial.domain.Director;
import com.hibernate4all.tutorial.repository.jpa.DirectorRepositoryJpa;
import com.hibernate4all.tutorial.repository.jpa.specifications.DirectorSpecifications;
import com.hibernate4all.tutorial.service.DirectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepositoryJpa directorRepositoryJpa;

    public DirectorServiceImpl(DirectorRepositoryJpa directorRepositoryJpa) {
        this.directorRepositoryJpa = directorRepositoryJpa;
    }

    @Override
    public Director saveDirector(Director director) {
        return this.directorRepositoryJpa.save(director);
    }

    @Override
    public List<Director> directorsByMovieTitle(String title, String directorName) {
        return this.directorRepositoryJpa.findAll(DirectorSpecifications.findDirectorByMovieTitle(title, directorName));
    }
}
