package com.sd.energy.controller;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.dto.EnergyConsumptionDto;
import com.sd.energy.services.DeviceService;
import com.sd.energy.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RolesAllowed("USER")
@CrossOrigin
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final DeviceService deviceService;

    @GetMapping("/{id}/devices")
    public List<DeviceDto> getDevices(@PathVariable Long id) {
        return userService.findAllDevicesForUser(id);
    }

    @GetMapping(value = "/device/{id}/consumption", params = "date")
    public List<EnergyConsumptionDto> getEnergyConsumption(@PathVariable Long id, @Param("data") String date) {
        return deviceService.findAllConsumptionForIdAndDate(id, date);
    }
}
