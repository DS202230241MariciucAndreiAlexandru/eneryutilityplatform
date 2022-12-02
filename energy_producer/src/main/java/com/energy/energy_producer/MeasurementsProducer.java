package com.energy.energy_producer;


import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeasurementsProducer implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    void send() {
        rabbitTemplate.convertAndSend("Hello world!\n");
        System.out.println("Acum trebuie sa trimit!");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Acum trebuie sa trimit1!");
        rabbitTemplate.convertAndSend("measurements_queue", "Hello world\n");
        System.exit(0);
    }
}
