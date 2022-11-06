package com.sd.energy.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(Long id,
                      String username,
                      String role,
                      Set<DeviceDto> devices) {

}
