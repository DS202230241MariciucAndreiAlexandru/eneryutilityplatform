package com.sd.energy.security.service.impl;

import com.sd.energy.domain.dto.RegisterUserRequest;
import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.mapper.SecureUserMapper;
import com.sd.energy.domain.model.User;
import com.sd.energy.security.repository.UserRepository;
import com.sd.energy.security.service.AuthUserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

    private final UserRepository userRepository;
    private final SecureUserMapper secureUserMapper;


    @Override
    public UserDto registerUser(RegisterUserRequest userRequest) {
        if (userRepository.findByUsername(userRequest.username()).isPresent()) {
            throw new ValidationException("Username exists!");
        }

        User user = secureUserMapper.fromRegisterRequest(userRequest);

        return secureUserMapper.toUserDto(userRepository.save(user));
    }
}
