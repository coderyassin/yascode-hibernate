package com.hibernate4all.tutorial.controller;

import com.hibernate4all.tutorial.controller.response.DirectorResponse;
import com.hibernate4all.tutorial.domain.Director;
import com.hibernate4all.tutorial.repository.jpa.projections.DirectorProjection;
import com.hibernate4all.tutorial.service.DirectorService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/all/{page}/{size}")
    public DirectorResponse directors(@PathVariable("page") int page,
                                    @PathVariable("size") int size) {
        Map<String, Object> directors = this.directorService.directors(page, size);
        return DirectorResponse.builder()
                .directors((List<Director>) directors.get("content"))
                .totalElements((Long) directors.get("totalElements"))
                .totalPages((Integer) directors.get("totalPages"))
                .build();
    }

    @GetMapping(value = "/all/filter")
    public DirectorResponse directorsWithoutPathVariables(@RequestParam(value = "page", defaultValue = "0", required = false) Optional<Integer> page,
                                                          @RequestParam(value = "size", defaultValue = "10", required = false) Optional<Integer> size) {
        Map<String, Object> directors = this.directorService.directors(page.get(), size.get());
        return DirectorResponse.builder()
                .directors((List<Director>) directors.get("content"))
                .totalElements((Long) directors.get("totalElements"))
                .totalPages((Integer) directors.get("totalPages"))
                .build();
    }

    @GetMapping("/test")
    public Page<DirectorProjection> test() {
        return this.directorService.getFirstNameAndLastName();
    }

}
