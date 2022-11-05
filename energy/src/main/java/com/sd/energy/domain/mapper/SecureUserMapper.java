package com.sd.energy.domain.mapper;

import com.sd.energy.domain.dto.RegisterUserRequest;
import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.model.Role;
import com.sd.energy.domain.model.User;
import com.sd.energy.security.repository.RoleRepository;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class SecureUserMapper {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RoleRepository roleRepository;

    @Mapping(source = "role", target = "authorities", qualifiedByName = "rolesToDbRoles")
    @Mapping(source = "password", target = "password", qualifiedByName = "passwordToEncodePassword")
    public abstract User fromRegisterRequest(RegisterUserRequest userRequest);

    @Mapping(source = "authorities ", target = "role", qualifiedByName = "rolePopulator")
    public abstract UserDto toUserDto(User user);

    @Named(value = "rolePopulator")
    protected String rolePopulator(Collection<Role> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).findAny().orElse(null);
    }

    @Named("passwordToEncodePassword")
    protected String passwordToEncodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Named("rolesToDbRoles")
    protected Set<Role> rolesToDbRoles(String role) {
        return roleRepository.findByRoleName(role).map(Set::of).orElse(Collections.emptySet());
    }
}
