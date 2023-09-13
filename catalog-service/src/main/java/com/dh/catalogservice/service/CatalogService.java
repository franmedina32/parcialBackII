package com.dh.catalogservice.service;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Catalogo;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.MovieRepository;
import com.dh.catalogservice.repository.SerieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CatalogService implements ICatalogService{


    private final IMovieClient movieClient;
    private final ISerieClient serieClient;
    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;


    @Override
    public Catalogo listarPorGenero(String genero) {
        Catalogo catalogo = new Catalogo();
        List<Serie> serieList = serieRepository.findAllByGenre(genero);
        List<Movie> movieList = movieRepository.findAllByGenre(genero);
        catalogo.setMovieList(movieList);
        catalogo.setSerieList(serieList);
        return catalogo;
    }

    @Override
    public Serie guardarSerie(Serie serie) {
        return serieRepository.save(serie);
    }

    @Override
    public Movie guardarMovie(Movie movie) {
        return movieRepository.save(movie);
    }





}
