package com.dh.catalogservice.service;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Catalogo;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.MovieRepository;
import com.dh.catalogservice.repository.SerieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.logging.Logger;


@Service
@AllArgsConstructor
public class CatalogService implements ICatalogService{


    private final IMovieClient movieClient;
    private final ISerieClient serieClient;
    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @CircuitBreaker(name = "catalogs", fallbackMethod = "emptyList")
    @Retry(name = "catalogs")
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

    private Catalogo emptyList(CallNotPermittedException exception) {
        //"Error creating Catalog with that genre";
        return new Catalogo();
    }

}

//      JUSTIFICATIVO SOBRE LA POSICION ELEGIDA DEL CIRCUIT BRAKER

//Decidimos poner el circuit braker en el catalogo ya que creemos que es el
// microservicio principal  donde van a pasar todas las peticiones y, además
//el método para buscar por genero será el más utilizado.