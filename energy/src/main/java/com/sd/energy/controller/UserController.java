package com.sd.energy.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RolesAllowed("ADMIN")
@CrossOrigin
public class UserController {

    @GetMapping("/user")
    public String get() {
        return "Sunt un user!";
    }
}
