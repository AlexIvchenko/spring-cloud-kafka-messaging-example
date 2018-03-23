package com.github.alexivchenko.examples.spring.cloud.kafka.message.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableBinding(Source.class)
@SpringBootApplication
public class MessageRestApplication {
    private final Source source;

    public MessageRestApplication(Source source) {
        this.source = source;
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageRestApplication.class, args);
    }

    @PostMapping
    public String publishMessage(@RequestBody String message) {
        log.info("sent message \"{}\" to broker", message);
        source.output().send(MessageBuilder.withPayload(message).build());
        return message;
    }
}
