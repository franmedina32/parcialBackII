package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {
    @Autowired
    private IMovieClient movieClient;

    @GetMapping("catalog/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre){
        return movieClient.getMovieByGenre(genre);
    };

    @PostMapping("catalog/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        return movieClient.saveMovie(movie);
    };
}