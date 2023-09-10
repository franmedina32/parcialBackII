package com.dh.catalogservice.client;

import com.dh.catalogservice.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="serie-service")
public interface ISerieClient{


    @GetMapping
    public List<Serie> getAll();

    @GetMapping("api/v1/series/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie);
}
