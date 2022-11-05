package com.sd.energy.config;

import com.sd.energy.domain.model.Role;
import com.sd.energy.security.repository.RoleRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class InitRoles {

    @Resource
    private RoleRepository roleRepository;

    @PostConstruct
    void init() {
        if (roleRepository.findByRoleName(Role.ADMIN).isEmpty()) {
            Role adminRole = new Role();
            adminRole.setAuthority(Role.ADMIN);
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByRoleName(Role.USER).isEmpty()) {
            Role userRole = new Role();
            userRole.setAuthority(Role.USER);
            roleRepository.save(userRole);
        }
    }
}
