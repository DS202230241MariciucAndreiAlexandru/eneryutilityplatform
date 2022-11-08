package com.sd.energy.controller;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.model.Device;
import com.sd.energy.domain.model.User;
import com.sd.energy.repository.DeviceRepository;
import com.sd.energy.security.repository.UserRepository;
import com.sd.energy.services.DeviceService;
import com.sd.energy.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sd.energy.domain.model.Role.ADMIN;

@RestController
@RequestMapping("/admin")
@RolesAllowed(ADMIN)
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final DeviceService deviceService;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    @PutMapping("/update-user")
    public boolean updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @PutMapping("/user/{id}/update-devices")
    public boolean updateDevices(@RequestBody List<DeviceDto> devices, @PathVariable Long id) {
        return userService.updateUserDevices(id, devices);
    }

    @GetMapping("/{id}/devices")
    public List<DeviceDto> getDevicesForUser(@PathVariable Long id) {
        return deviceService.findAll(id);
    }

    @GetMapping("/devices")
    public List<DeviceDto> getDevices() {
        return deviceService.findAll();
    }

    @DeleteMapping("/delete-user/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        try {
            Optional<User> byId = userRepository.findById(id);
            byId.ifPresent(u -> {
                u.getDevices().forEach(d -> d.setUser(null));
                userRepository.delete(u);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/user/{id}")
    public UserDto getUserForDevice(@PathVariable Long id) {
        return deviceService.findByDevice(id);
    }

    @PutMapping("/user/{userId}/device/{deviceId}")
    public boolean updateDeviceWithUser(@PathVariable Long userId, @PathVariable Long deviceId) {
        return deviceService.updateDeviceWithUser(userId, deviceId);
    }

    @DeleteMapping("/delete-device/{id}")
    public boolean deleteDevice(@PathVariable Long id) {
        try {
            Device device = deviceRepository.findById(id).get();

            device.getEnergyConsumption().forEach(en -> en.setDevice(null));
            deviceRepository.delete(device);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/add-device")
    public DeviceDto addDevice(@RequestBody DeviceDto deviceDto) {
        return deviceService.createOrUpdate(deviceDto);
    }
}
