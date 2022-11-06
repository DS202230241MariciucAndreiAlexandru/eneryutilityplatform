package com.sd.energy.domain.mapper;

import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.model.Role;
import com.sd.energy.domain.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = DeviceMapper.class)
public abstract class UserMapper {

    @Mapping(target = "role", source = "authorities", qualifiedByName = "rolePopulator")
    public abstract UserDto toUserDto(User user);

    @Named(value = "rolePopulator")
    protected String rolePopulator(Collection<Role> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).findAny().orElse(null);
    }
}
