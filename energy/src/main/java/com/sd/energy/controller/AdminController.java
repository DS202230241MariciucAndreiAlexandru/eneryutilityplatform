package com.sd.energy.controller;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.services.DeviceService;
import com.sd.energy.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

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
    public List<DeviceDto> getDevices(@PathVariable Long id) {
        return deviceService.findAll(id);
    }
}
