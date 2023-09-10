package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {
    @Autowired
    private IMovieClient movieClient;
    private ISerieClient serieClient;

    @GetMapping("catalog/movie/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre){
        return movieClient.getMovieByGenre(genre);
    };

    @PostMapping("catalog/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        return movieClient.saveMovie(movie);
    };

    @GetMapping("catalog/allSerie")
    public List<Serie> getAll() {
        return serieClient.getAll();
    }

    @GetMapping("catalog/serie/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        return serieClient.getSerieByGenre(genre);
    }

    @PostMapping("catalog/crearSerie")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie) {
        serieClient.create(serie);
        return serie.getId();
    }
}
