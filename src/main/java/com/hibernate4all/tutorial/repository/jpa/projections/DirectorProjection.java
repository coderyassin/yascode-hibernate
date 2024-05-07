package com.hibernate4all.tutorial.repository.jpa.projections;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorProjection {

    private String firstName;
    private String lastName;

}
