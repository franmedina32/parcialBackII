package com.dh.catalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Catalogo {
    private List<Serie> serieList = new ArrayList<>();
    private List<Movie> movieList = new ArrayList<>();
}
