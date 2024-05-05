package com.hibernate4all.tutorial.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    /*@JsonIgnore*/
    private List<Movie> movies;
}
