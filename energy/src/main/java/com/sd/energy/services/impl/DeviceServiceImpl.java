package com.sd.energy.services.impl;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.dto.EnergyConsumptionDto;
import com.sd.energy.domain.mapper.DeviceMapper;
import com.sd.energy.domain.mapper.EnergyMapper;
import com.sd.energy.domain.model.Device;
import com.sd.energy.repository.DeviceRepository;
import com.sd.energy.services.DeviceService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final EnergyMapper energyMapper;

    @Override
    public List<DeviceDto> findAll(Long id) {
        return StreamSupport.stream(deviceRepository.findAll().spliterator(), false)
                            .filter(device -> device.getUser() == null || device.getUser().getId().equals(id))
                            .map(deviceMapper::deviceToDto)
                            .collect(Collectors.toList());
    }

    @Override
    public List<EnergyConsumptionDto> findAllConsumptionForIdAndDate(Long id, String date) {
        Optional<Device> byId = deviceRepository.findById(id);
        if (byId.isEmpty()) {
            return Collections.emptyList();
        }
        var device = byId.get();

        LocalDate ld = LocalDate.parse(date);

        return device.getEnergyConsumption()
                     .stream()
                     .filter(ec -> ec.getTimeStamp().isAfter(ld.atStartOfDay()) && ec.getTimeStamp().isBefore(ld.atTime(LocalTime.MAX)))
                     .map(energyMapper::toEnergyConsumptionDto)
                     .collect(Collectors.toList());
    }
}
