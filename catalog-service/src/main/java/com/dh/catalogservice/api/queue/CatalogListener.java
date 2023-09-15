package com.dh.catalogservice.api.queue;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;



@AllArgsConstructor
@Component
public class CatalogListener {

    private final CatalogService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogListener.class);


    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receive(@Payload Movie movie) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("Received message -> %s", movie));
        service.guardarMovie(movie);
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receive(@Payload Serie serie) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("Received message -> %s", serie));
        service.guardarSerie(serie);
    }
}
