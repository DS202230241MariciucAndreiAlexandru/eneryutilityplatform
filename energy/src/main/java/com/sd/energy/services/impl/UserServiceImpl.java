package com.sd.energy.services.impl;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.mapper.DeviceMapper;
import com.sd.energy.domain.mapper.UserMapper;
import com.sd.energy.domain.model.User;
import com.sd.energy.repository.DeviceRepository;
import com.sd.energy.security.repository.UserRepository;
import com.sd.energy.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final UserMapper userMapper;
    private final DeviceMapper deviceMapper;

    @Override
    public List<UserDto> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                            .map(userMapper::toUserDto)
                            .collect(Collectors.toList());
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        Optional<User> byId = userRepository.findById(userDto.id());
        if (byId.isEmpty()) {
            return false;
        }

        User user = byId.get();
        user.setUsername(userDto.username());
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateUserDevices(Long id, List<DeviceDto> devices) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            return false;
        }

        var newDevices = devices.stream()
                                .filter(DeviceDto::checked)
                                .map(DeviceDto::id)
                                .map(deviceRepository::findById)
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .collect(Collectors.toSet());

        var user = byId.get();
        user.setDevices(newDevices);

        userRepository.save(user);

        return true;
    }

    @Override
    public List<DeviceDto> findAllDevicesForUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            return Collections.emptyList();
        }

        return byId.get().getDevices().stream()
                   .map(deviceMapper::deviceToDto).collect(Collectors.toList());
    }
}
