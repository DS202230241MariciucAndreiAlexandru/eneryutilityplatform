package com.sd.energy.config;

import com.sd.energy.domain.model.EnergyConsumption;
import com.sd.energy.repository.DeviceRepository;
import com.sd.energy.repository.EnergyConsumptionRepository;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
public class Scheduler {

    @Resource
    private DeviceRepository deviceRepository;

    @Resource
    private EnergyConsumptionRepository energyConsumptionRepository;

    @Scheduled(fixedRate = 1000 * 60 * 5)
    void populateSomeValue() {
        StreamSupport.stream(deviceRepository.findAll().spliterator(), false)
                     .forEach(device -> {
                         var en = new EnergyConsumption();
                         en.setTimeStamp(LocalDateTime.now());
                         en.setEnergy(new Random().nextDouble());
                         en.setDevice(device);
                         energyConsumptionRepository.save(en);
                     });
    }
}
