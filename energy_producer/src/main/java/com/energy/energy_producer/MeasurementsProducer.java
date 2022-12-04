package com.energy.energy_producer;


import com.energy.energy_producer.model.EnergyConsumption;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MeasurementsProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("classpath:data/sensor.csv")
    Resource sensorsData;

    @Value("${device.id}")
    String deviceIdString;

    @PostConstruct
    void send() throws IOException {
        long deviceId = Long.parseLong(deviceIdString);

        List<String> allLines = Files.readAllLines(sensorsData.getFile().toPath());
        log.info("Sending...");

        allLines.stream()
                .map(Double::parseDouble)
                .map(energy -> new EnergyConsumption(deviceId, LocalDateTime.now().toString(), energy))
                .forEach(this::sendMeasurement);

        log.info("Exiting...");
        System.exit(0);
    }
    //
    //    @Override
    //    public void run(String... args) throws Exception {
    //        send();
    //    }

    public void sendMeasurement(EnergyConsumption energyConsumption) {
        Gson json = new Gson();
        String data = null;

        data = json.toJson(energyConsumption);

        log.info("SENDING: " + data);

        rabbitTemplate.convertAndSend("measurements_topic", "measurements_key", data);

        try {
            Thread.sleep(10000);
        } catch (Exception e) {
        }
    }
}
