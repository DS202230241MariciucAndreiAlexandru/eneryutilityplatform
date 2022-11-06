package com.sd.energy.domain.mapper;

import com.sd.energy.domain.dto.EnergyConsumptionDto;
import com.sd.energy.domain.model.EnergyConsumption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class EnergyMapper {

    @Mapping(source = "timeStamp", target = "timeStamp", qualifiedByName = "justHour")
    public abstract EnergyConsumptionDto toEnergyConsumptionDto(EnergyConsumption energyConsumption);

    @Named("justHour")
    protected String justHour(LocalDateTime ldt) {
        return ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
