package com.hibernate4all.tutorial.controller;

import com.hibernate4all.tutorial.domain.Director;
import com.hibernate4all.tutorial.service.DirectorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/directors")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping(value = {"/directorsByMovieTitle/{title}/{directorName}", "directorsByMovieTitle"})
    public List<Director> directorsByMovieTitleAndDirectorName(@PathVariable("title") String title,
                                                               @PathVariable("directorName") String directorName) {
        return this.directorService.directorsByMovieTitle(title, directorName);
    }

}
