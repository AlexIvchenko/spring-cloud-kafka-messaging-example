package com.github.alexivchenko.examples.spring.cloud.kafka.message.transformer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import reactor.core.publisher.Flux;

@Slf4j
@EnableBinding(Processor.class)
@SpringBootApplication
public class ReactiveUpperCaseTransformerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveUpperCaseTransformerApplication.class, args);
    }

    @StreamListener
    @Output(Processor.OUTPUT)
    public Flux<String> receive(@Input(Processor.INPUT) Flux<String> input) {
        return input
                .doOnNext(str -> log.info("received: \"{}\"", str))
                .map(String::toUpperCase)
                .doOnNext(str -> log.info("transformed: \"{}\"", str));
    }
}
