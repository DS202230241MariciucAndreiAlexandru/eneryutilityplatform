package com.sd.energy.services.impl;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.dto.EnergyConsumptionDto;
import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.mapper.DeviceMapper;
import com.sd.energy.domain.mapper.EnergyMapper;
import com.sd.energy.domain.mapper.UserMapper;
import com.sd.energy.domain.model.Address;
import com.sd.energy.domain.model.Device;
import com.sd.energy.domain.model.User;
import com.sd.energy.repository.AddressRepository;
import com.sd.energy.repository.DeviceRepository;
import com.sd.energy.security.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final EnergyMapper energyMapper;
    private final UserMapper userMapper;
    private final AddressRepository addressRepository;

    @Override
    public List<DeviceDto> findAll(Long id) {
        return StreamSupport.stream(deviceRepository.findAll().spliterator(), false)
                            .filter(device -> device.getUser() == null || device.getUser().getId().equals(id))
                            .map(deviceMapper::deviceToDto)
                            .collect(Collectors.toList());
    }

    @Override
    public List<DeviceDto> findAll() {
        return StreamSupport.stream(deviceRepository.findAll().spliterator(), false)
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

    @Override
    public UserDto findByDevice(Long id) {
        Optional<Device> byId = deviceRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        var d = byId.get();
        var u = d.getUser();

        if (u == null) {
            return null;
        }

        return userMapper.toUserDto(u);
    }

    @Override
    public boolean updateDeviceWithUser(Long userId, Long deviceId) {
        User user = userRepository.findById(userId).get();
        Device device = deviceRepository.findById(deviceId).get();

        device.setUser(user);
        deviceRepository.save(device);

        return true;
    }

    @Override
    public DeviceDto createOrUpdate(DeviceDto deviceDto) {
        Device d;
        if (deviceDto.id() != null) {
            d = deviceRepository.findById(deviceDto.id()).get();
            d.setDescription(deviceDto.description());
        } else {
            d = new Device();
            d.setDescription(deviceDto.description());
        }

        if (d.getAddress() == null) {
            var a = new Address();
            addressRepository.save(a);
            a.setName(deviceDto.address().name());
            d.setAddress(a);
        } else {
            Address address = d.getAddress();
            address.setName(deviceDto.address().name());
        }

        d.setMaxEnergyConsumption(deviceDto.maxEnergyConsumption());

        return deviceMapper.deviceToDto(deviceRepository.save(d));
    }
}
