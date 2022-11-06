package com.sd.energy.services;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    boolean updateUser(UserDto userDto);

    boolean updateUserDevices(Long id, List<DeviceDto> deviceDtos);

    List<DeviceDto> findAllDevicesForUser(Long id);
}
