package com.sd.energy.security.service;

import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.model.User;

import org.springframework.data.util.Pair;

public interface JwtTokenService {

    Pair<UserDto, String> generateTokenForUser(User user);
}
