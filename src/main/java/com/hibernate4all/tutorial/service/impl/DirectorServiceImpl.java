package com.hibernate4all.tutorial.service.impl;

import com.hibernate4all.tutorial.domain.Director;
import com.hibernate4all.tutorial.repository.DirectorRepository;
import com.hibernate4all.tutorial.repository.jpa.DirectorRepositoryJpa;
import com.hibernate4all.tutorial.repository.jpa.projections.DirectorProjection;
import com.hibernate4all.tutorial.repository.jpa.specifications.DirectorSpecifications;
import com.hibernate4all.tutorial.service.DirectorService;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepositoryJpa directorRepositoryJpa;
    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepositoryJpa directorRepositoryJpa, DirectorRepository directorRepository) {
        this.directorRepositoryJpa = directorRepositoryJpa;
        this.directorRepository = directorRepository;
    }

    @Override
    public Director saveDirector(Director director) {
        return this.directorRepositoryJpa.save(director);
    }

    @Override
    public List<Director> findDirectorsByFirstName(String firstName) {
        return this.directorRepository.findByFirstName(firstName)
                .stream()
                .map(director -> {
                    director.setMovies(null);
                    return director;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Director> findDirectorsByLastName(String lastName) {
        return this.directorRepository.findByLastName(lastName)
                .stream()
                .map(director -> {
            director.setMovies(null);
            return director;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Director> directorsByMovieTitle(String title, String directorFirstName, String directorLastName) {
        List<Director> directors = this.directorRepositoryJpa.findAll(DirectorSpecifications.findDirectorByMovieTitle(title, directorFirstName, directorLastName));
        return directors.stream().map(d -> {
            d.setMovies(d.getMovies().stream().map(m -> {
               m.setDirector(null);
               return m;
            }).collect(Collectors.toList()));
            return d;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Director> findByMovieTitle(String title, String directorFirstName, String directorLastName) {
        return this.directorRepositoryJpa.findByMovieName(title)
                .stream()
                .map(director -> {
                    director.setMovies(null);
                    return director;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Director> findDirectorsByFirstNameAndLastName(String firstName, String lastName) {
        return this.directorRepository.findDirectorsByFirstNameAndLastName(firstName, lastName)
                .stream()
                .map(director -> {
                    director.setMovies(null);
                    return director;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Director> findAllDirectorsWithConjunction() {
        return this.directorRepositoryJpa.findAll(DirectorSpecifications.findAllDirectors())
                .stream()
                .map(director -> {
                    director.setMovies(null);
                    return director;
                }).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> directors(int pageNumber, int pageSize) {
        Page<Director> directorsPage = this.directorRepositoryJpa.findAll(PageRequest.of(pageNumber, pageSize));

        return Map.of("content", directorsPage.getContent().stream()
                .map(director -> {
                    director.setMovies(null);
                    return director;
                }).collect(Collectors.toList()),
                "totalElements", directorsPage.getTotalElements(),
                "totalPages", directorsPage.getTotalPages());
    }

    @Override
    public Page<DirectorProjection> getFirstNameAndLastName() {
        return this.directorRepositoryJpa.findFirstNameAndLastName(PageRequest.of(0,10));
    }

    @PostConstruct
    private int directorsTest() {
        Page<DirectorProjection> idAndFirstNameAndLastName = this.directorRepositoryJpa.findFirstNameAndLastName(PageRequest.of(0,10));
        return 10;
    }
}
