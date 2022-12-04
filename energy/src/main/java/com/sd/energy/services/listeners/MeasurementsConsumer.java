package com.sd.energy.services.listeners;

import com.google.gson.Gson;
import com.sd.energy.domain.dto.EnergyConsumptionRabbit;
import com.sd.energy.domain.model.EnergyConsumption;
import com.sd.energy.repository.DeviceRepository;
import com.sd.energy.repository.EnergyConsumptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "measurements_queue")
@RequiredArgsConstructor
@Slf4j
public class MeasurementsConsumer {

    private final DeviceRepository deviceRepository;
    private final EnergyConsumptionRepository energyConsumptionRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @RabbitHandler
    void receive(String message) {
        Gson gson = new Gson();
        var energyConsumption = gson.fromJson(message, EnergyConsumptionRabbit.class);
        var energy = toEnergyConsumption(energyConsumption);

        log.info("SAVING..." + energy);
        energyConsumptionRepository.save(energy);

        simpMessagingTemplate.convertAndSend("/warns/alert", "asta e o alerta!");
    }

    EnergyConsumption toEnergyConsumption(EnergyConsumptionRabbit rabbitEnergy) {
        var e = new EnergyConsumption();
        e.setEnergy(rabbitEnergy.energy());
        e.setTimeStamp(LocalDateTime.parse(rabbitEnergy.timestamp()));
        var deviceOp = deviceRepository.findById(rabbitEnergy.id());
        deviceOp.ifPresent(e::setDevice);
        return e;
    }

}
