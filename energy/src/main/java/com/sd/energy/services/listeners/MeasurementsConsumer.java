package com.sd.energy.services.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "measurements_queue")
public class MeasurementsConsumer {

    @RabbitHandler
    void receive(String message) {
        System.out.println(message);
    }

}
