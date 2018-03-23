package com.github.alexivchenko.examples.spring.cloud.kafka.message.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@EnableBinding(Source.class)
@Service
public class Emitter {
    @StreamEmitter
    @Output(Source.OUTPUT)
    public Flux<String> messageEmite() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(l -> {
                    String message = UUID.randomUUID().toString();
                    log.info("sent message \"{}\" to broker", message);
                    return message;
                });
    }
}
