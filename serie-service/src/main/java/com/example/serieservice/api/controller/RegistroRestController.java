package com.example.serieservice.api.controller;


import com.example.serieservice.api.queue.SerieSender;
import com.example.serieservice.model.Serie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistroRestController {

    private final SerieSender sender;

    @PostMapping("/save")
    public ResponseEntity<?> saveSerie(@RequestBody Serie serie) {
        sender.sendMsg(serie);
        return ResponseEntity.noContent().build();
    }


}
