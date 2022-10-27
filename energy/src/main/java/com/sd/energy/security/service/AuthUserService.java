package com.sd.energy.security.service;

import com.sd.energy.domain.dto.RegisterUserRequest;
import com.sd.energy.domain.dto.UserDto;

public interface AuthUserService {
    UserDto registerUser(RegisterUserRequest userRequest);
}
