package com.sd.energy.domain.dto;

import javax.validation.constraints.NotBlank;

public record RegisterUserRequest(@NotBlank String username,
                                  @NotBlank String password,
                                  String role) {

}
