package com.sd.energy.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sd.energy.domain.model.Role.ADMIN;

@RestController
@RolesAllowed(ADMIN)
public class AdminController {

    @GetMapping("/admin")
    public String get() {
        return "Sunt admin";
    }
}
