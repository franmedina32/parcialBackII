package com.dh.movieservice.api.controller;

import com.dh.movieservice.api.queue.MovieSender;
import com.dh.movieservice.model.Movie;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistroRestController {

    private final MovieSender sender;

    @PostMapping("/save")
    public ResponseEntity<?> savePersona(@RequestBody Movie movie) {
        sender.sendMsg(movie);
        return ResponseEntity.noContent().build();
    }

}
