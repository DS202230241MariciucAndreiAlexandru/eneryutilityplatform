package com.sd.energy.domain.mapper;

import com.sd.energy.domain.dto.DeviceDto;
import com.sd.energy.domain.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {AddressMapper.class})
public abstract class DeviceMapper {

    public abstract DeviceDto deviceToDto(Device device);
}
