package com.sd.energy.config;

import com.sd.energy.domain.model.Address;
import com.sd.energy.domain.model.Device;
import com.sd.energy.domain.model.Role;
import com.sd.energy.domain.model.User;
import com.sd.energy.repository.AddressRepository;
import com.sd.energy.repository.DeviceRepository;
import com.sd.energy.security.repository.RoleRepository;
import com.sd.energy.security.repository.UserRepository;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SampleData {

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private DeviceRepository deviceRepository;

    @Resource
    private AddressRepository addressRepository;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    private void initRole() {
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

    void initUsers() {

        var admin1 = new User();
        admin1.setAuthorities(Set.of(roleRepository.findByRoleName(Role.ADMIN).get()));
        admin1.setUsername("admin");
        admin1.setPassword(passwordEncoder.encode("admin"));

        if (userRepository.findByUsername(admin1.getUsername()).isEmpty()) {
            userRepository.save(admin1);
        }

        var user = new User();
        user.setUsername("andrei");
        user.setPassword(passwordEncoder.encode("andrei"));
        user.setAuthorities(Set.of(roleRepository.findByRoleName(Role.USER).get()));

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return;
        }

        user = userRepository.save(user);

        var address = new Address();

        address.setName("Baritiu");
        address = addressRepository.save(address);

        var device1 = new Device();
        device1.setDescription("Samsung");
        device1.setAddress(address);
        device1.setUser(user);

        var device2 = new Device();
        device2.setDescription("Apple");
        device2.setAddress(address);
        device2.setUser(user);

        deviceRepository.saveAll(List.of(device1, device2));
    }

    @PostConstruct
    void init() {
        initRole();
        initUsers();
    }
}
