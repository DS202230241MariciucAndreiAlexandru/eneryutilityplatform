package com.sd.energy.services.impl;

import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.mapper.UserMapper;
import com.sd.energy.security.repository.UserRepository;
import com.sd.energy.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                            .map(userMapper::toUserDto)
                            .collect(Collectors.toList());
    }
}
