package com.sd.energy.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DeviceDto(Long id,
                        String description,
                        AddressDto address,
                        Boolean checked) {

}
