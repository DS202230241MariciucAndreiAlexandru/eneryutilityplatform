package com.sd.energy.services;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.dto.EnergyConsumptionDto;

import java.util.List;

public interface DeviceService {

    List<DeviceDto> findAll(Long id);

    List<EnergyConsumptionDto> findAllConsumptionForIdAndDate(Long id, String date);
}
