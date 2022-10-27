package com.sd.energy.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "energyconsuptions")
@Data
public class EnergyConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDateTime timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;
}
