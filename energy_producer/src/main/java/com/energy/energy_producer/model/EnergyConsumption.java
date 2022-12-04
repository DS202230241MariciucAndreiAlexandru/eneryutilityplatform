package com.energy.energy_producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnergyConsumption {

    private long id;
    private String timestamp;
    private double energy;
}
