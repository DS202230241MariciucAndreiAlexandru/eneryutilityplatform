package com.sd.energy.repository;

import com.sd.energy.domain.model.EnergyConsumption;

import org.springframework.data.repository.CrudRepository;

public interface EnergyConsumptionRepository extends CrudRepository<EnergyConsumption, Long> {

}
