package com.sd.energy.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(Long id,
                      String username,
                      String role) {
}
