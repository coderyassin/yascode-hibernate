package com.hibernate4all.tutorial.controller.response;

import com.hibernate4all.tutorial.domain.Director;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorResponse {

    private List<Director> directors;
    private Long totalElements;
    private int totalPages;

}
