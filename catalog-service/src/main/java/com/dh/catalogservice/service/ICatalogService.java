package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Catalogo;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;


import java.util.List;
import java.util.Objects;

public interface ICatalogService {

    public Catalogo listarPorGenero(String genero);

    public Serie guardarSerie(Serie serie);
    public Movie guardarMovie(Movie movie);
}
