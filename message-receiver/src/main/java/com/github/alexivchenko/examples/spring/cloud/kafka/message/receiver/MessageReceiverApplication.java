package com.github.alexivchenko.examples.spring.cloud.kafka.message.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(Sink.class)
@SpringBootApplication
public class MessageReceiverApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageReceiverApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void log(String message) {
        log.info("received message \"{}\" from broker", message);
    }
}
