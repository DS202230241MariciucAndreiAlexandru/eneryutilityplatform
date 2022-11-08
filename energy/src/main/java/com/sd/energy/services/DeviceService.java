package com.sd.energy.services;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.dto.EnergyConsumptionDto;
import com.sd.energy.domain.dto.UserDto;

import java.util.List;

public interface DeviceService {

    List<DeviceDto> findAll(Long id);

    List<DeviceDto> findAll();

    List<EnergyConsumptionDto> findAllConsumptionForIdAndDate(Long id, String date);

    UserDto findByDevice(Long id);

    boolean updateDeviceWithUser(Long userId, Long deviceId);
}
