package com.dh.catalogservice.controller;

import com.dh.catalogservice.api.queue.CatalogListener;
import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Catalogo;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
public class CatalogController {

    private IMovieClient movieClient;
    private ISerieClient serieClient;
    private final CatalogListener listener;
    private CatalogService catalogService;


    @PostMapping("catalog/crearMovie")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        listener.receive(movie);
        return movieClient.saveMovie(movie);
    };

    @PostMapping("catalog/crearSerie")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie) {
        listener.receive(serie);
        serieClient.create(serie);
        return serie.getId();
    }

    @GetMapping("catalog/{genre}")
    public ResponseEntity<Catalogo> listarCatalog(@PathVariable String genre){
        return ResponseEntity.ok(catalogService.listarPorGenero(genre));
    }
}
