package com.sd.energy.domain.dto;

import javax.validation.constraints.NotBlank;

public record AuthRequest(
    @NotBlank String username,
    @NotBlank String password) {

}
