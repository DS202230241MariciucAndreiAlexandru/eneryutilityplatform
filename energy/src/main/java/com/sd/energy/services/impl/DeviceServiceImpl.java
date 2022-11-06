package com.sd.energy.services.impl;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.mapper.DeviceMapper;
import com.sd.energy.repository.DeviceRepository;
import com.sd.energy.services.DeviceService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private final DeviceRepository deviceRepository;

    @Autowired
    private final DeviceMapper deviceMapper;

    @Override
    public List<DeviceDto> findAll() {
        return StreamSupport.stream(deviceRepository.findAll().spliterator(), false)
                            .map(deviceMapper::deviceToDto)
                            .collect(Collectors.toList());
    }
}
