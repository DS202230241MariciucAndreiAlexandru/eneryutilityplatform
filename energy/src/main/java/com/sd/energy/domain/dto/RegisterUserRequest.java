package com.sd.energy.domain.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public record RegisterUserRequest(@NotBlank String username,
                                  @NotBlank String password,
                                  Set<String> authorities) {
}
