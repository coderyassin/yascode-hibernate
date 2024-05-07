package com.hibernate4all.tutorial;

import com.hibernate4all.tutorial.domain.Director;
import com.hibernate4all.tutorial.domain.Movie;
import com.hibernate4all.tutorial.domain.Vehicle;
import com.hibernate4all.tutorial.repository.jpa.VehicleRepositoryJpa;
import com.hibernate4all.tutorial.service.DirectorService;
import com.hibernate4all.tutorial.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootApplication
public class TutorialApplication implements CommandLineRunner {
	private final DirectorService directorService;
	private final MovieService movieService;
	private final VehicleRepositoryJpa vehicleRepositoryJpa;

    public TutorialApplication(DirectorService directorService,
                               MovieService movieService,
							   VehicleRepositoryJpa vehicleRepositoryJpa) {
        this.directorService = directorService;
        this.movieService = movieService;
        this.vehicleRepositoryJpa = vehicleRepositoryJpa;
    }

    public static void main(String[] args) {
		SpringApplication.run(TutorialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<String> firstNames = new ArrayList<>(Arrays.asList("Yassin" ,"Michel", "Pierre", "Jean-Baptiste"));
		List<String> lastNames = new ArrayList<>(Arrays.asList("Mellouki", "Tremblay", "Dumas", "Rochefort"));

		int size = 0;
		while(size < 5) {
			List<String> cloneFN = firstNames.stream().collect(Collectors.toList());
			List<String> cloneLN = lastNames.stream().collect(Collectors.toList());

			cloneFN.stream().forEach(fn -> firstNames.add(fn));
			cloneLN.stream().forEach(fn -> lastNames.add(fn));

			size ++;
		}

		AtomicInteger j = new AtomicInteger();
		List<Director> directors = firstNames.stream().map(firstName -> {
			j.getAndIncrement();
			return directorService.saveDirector(Director.builder()
                .firstName(firstName)
                .lastName(lastNames.get(j.get() - 1))
                .build()); }).collect(Collectors.toList());

		System.out.println("Le sauvegarde des réalisateurs est terminé");


		List<String> nameMovies = Arrays.asList("Winter Break", "Killers of the Flower Moon", "King's Land", " Perfect Days", "Godzilla Minus One");

		List<LocalDate> dates = Arrays.asList(LocalDate.of(2023, 12, 12),
				LocalDate.of(2012, 6, 15),
				LocalDate.of(2014, 11, 26),
				LocalDate.of(2016, 9, 8),
				LocalDate.of(2009, 2, 24));

		AtomicInteger i = new AtomicInteger();

		nameMovies.stream().map(movie -> Movie.builder().name(movie).build())
				.map(movie -> {
					/*movie.setReleaseDate(dates.get(i.get()));
					if(i.get() > (directors.size() - 1)) {
						movie.setDirector(directors.get(0));
					} else {
						movie.setDirector(directors.get(i.get()));
					}*/
					movie.setDirector(directors.get(i.get()));
					i.getAndIncrement();
					return movie;
				}).forEach(movie -> movieService.saveMovie(movie));

		Vehicle vehicle1 = Vehicle.builder().
		type("Car").brand("Toyota")
				.model("Camry")
				.horsepower(200)
				.build();

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setType("Truck");
		vehicle2.setBrand("Ford");
		vehicle2.setModel("F-150");
		vehicle2.setHorsepower(300);

		Vehicle vehicle3 = new Vehicle();
		vehicle3.setType("Motorcycle");
		vehicle3.setBrand("Honda");
		vehicle3.setModel("CBR600RR");
		vehicle3.setHorsepower(120);

		Vehicle vehicle4 = new Vehicle();
		vehicle4.setType("SUV");
		vehicle4.setBrand("Jeep");
		vehicle4.setModel("Wrangler");
		vehicle4.setHorsepower(250);

		this.vehicleRepositoryJpa.save(vehicle1);
		this.vehicleRepositoryJpa.save(vehicle2);
		this.vehicleRepositoryJpa.save(vehicle3);
		this.vehicleRepositoryJpa.save(vehicle4);

	}
}
