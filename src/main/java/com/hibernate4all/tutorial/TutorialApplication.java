package com.hibernate4all.tutorial;

import com.hibernate4all.tutorial.domain.Director;
import com.hibernate4all.tutorial.domain.Movie;
import com.hibernate4all.tutorial.service.DirectorService;
import com.hibernate4all.tutorial.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootApplication
public class TutorialApplication implements CommandLineRunner {
	private final DirectorService directorService;
	private final MovieService movieService;

    public TutorialApplication(DirectorService directorService,
							   MovieService movieService) {
        this.directorService = directorService;
        this.movieService = movieService;
    }

    public static void main(String[] args) {
		SpringApplication.run(TutorialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<String> firstNames = Arrays.asList("Yassin" ,"Michel", "Pierre", "Jean-Baptiste");
		List<String> lastNames = Arrays.asList("Mellouki", "Tremblay", "Dumas", "Rochefort");

		AtomicInteger j = new AtomicInteger();
		List<Director> directors = firstNames.stream().map(firstName -> directorService.saveDirector(Director.builder()
                .firstName(firstName)
                .lastName(lastNames.get(j.get()))
                .build())).collect(Collectors.toList());


		List<String> nameMovies = Arrays.asList("Winter Break", "Killers of the Flower Moon", "King's Land", " Perfect Days", "Godzilla Minus One");

		List<LocalDate> dates = Arrays.asList(LocalDate.of(2023, 12, 12),
				LocalDate.of(2012, 6, 15),
				LocalDate.of(2014, 11, 26),
				LocalDate.of(2016, 9, 8),
				LocalDate.of(2009, 2, 24));

		AtomicInteger i = new AtomicInteger();

		nameMovies.stream().map(movie -> Movie.builder().name(movie).build())
				.map(movie -> {
					movie.setReleaseDate(dates.get(i.get()));
					if(i.get() > (directors.size() - 1)) {
						movie.setDirector(directors.get(0));
					} else {
						movie.setDirector(directors.get(i.get()));
					}
					i.getAndIncrement();
					return movie;
				}).forEach(movie -> movieService.saveMovie(movie));

	}
}
