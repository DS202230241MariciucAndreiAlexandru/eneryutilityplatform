package com.sd.energy.services;

import com.sd.energy.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();
}
