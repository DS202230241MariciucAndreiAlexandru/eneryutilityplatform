package com.sd.energy.domain.mapper;

import com.sd.energy.domain.dto.AddressDto;
import com.sd.energy.domain.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class AddressMapper {

    public abstract AddressDto addressToDto(Address address);
}
