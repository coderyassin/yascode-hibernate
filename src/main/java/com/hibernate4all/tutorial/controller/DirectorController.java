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

    @GetMapping(value = {"/directorsByMovieTitle/{title}/{directorFirstName}/{directorLastName}",
            "directorsByMovieTitle"})
    public List<Director> directorsByMovieTitleAndDirectorName(@PathVariable("title") String title,
                                                               @PathVariable("directorFirstName") String directorFirstName,
                                                               @PathVariable("directorLastName") String directorLastName) {
        return this.directorService.directorsByMovieTitle(title, directorFirstName, directorLastName);
    }

    @GetMapping(value = {"/directorsByFirstName/{firstName}", "/directorsByFirstName"})
    public List<Director> directorsByFirstName(@PathVariable("firstName") String firstName) {
        return this.directorService.findDirectorsByFirstName(firstName);
    }

    @GetMapping(value = {"/directorsByLastName/{lastName}", "/directorsByLastName"})
    public List<Director> directorsByLastName(@PathVariable("lastName") String lastName) {
        return this.directorService.findDirectorsByLastName(lastName);
    }

    @GetMapping(value = {"/directorsByFirstNameAndLastName/{firstName}/{lastName}", "/directorsByFirstNameAndLastName"})
    public List<Director> directorsByFirstNameAndLastName(@PathVariable("firstName") String firstName,
                                                          @PathVariable("lastName") String lastName) {
        return this.directorService.findDirectorsByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping(value = {"/directorsByMovieTitle/{title}", "directorsByMovieTitle"})
    public List<Director> directorsByMovieTitle(@PathVariable("title") String title) {
        return this.directorService.findByMovieTitle(title, "", "");
    }

    @GetMapping(value = {"/all"})
    public List<Director> directors() {
        return this.directorService.findAllDirectorsWithConjunction();
    }

}
