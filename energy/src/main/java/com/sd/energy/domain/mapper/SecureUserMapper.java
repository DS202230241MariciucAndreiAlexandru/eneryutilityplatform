package com.sd.energy.domain.mapper;

import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.model.Role;
import com.sd.energy.domain.model.User;
import com.sd.energy.domain.dto.RegisterUserRequest;
import com.sd.energy.security.repository.RoleRepository;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.security.auth.callback.LanguageCallback;

import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class SecureUserMapper {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RoleRepository roleRepository;

    public abstract UserDto toUserDto(User user);

    @Mapping(source = "authorities", target = "authorities", qualifiedByName = "rolesToDbRoles")
    @Mapping(source = "password", target = "password", qualifiedByName = "passwordToEncodePassword")
    public abstract User fromRegisterRequest(RegisterUserRequest userRequest);

    @Named("passwordToEncodePassword")
    protected String passwordToEncodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Named("rolesToDbRoles")
    protected Set<Role> rolesToDbRoles(Set<String> roles) {
        return roles.stream()
                    .map(role -> roleRepository.findByRoleName(role).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
    }
}
