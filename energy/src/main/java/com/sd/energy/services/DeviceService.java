package com.sd.energy.services;

import com.sd.energy.domain.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
    List<DeviceDto> findAll();
}
