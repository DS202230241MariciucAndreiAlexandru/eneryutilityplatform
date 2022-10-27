package com.sd.energy.security.service.impl;

import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.mapper.SecureUserMapper;
import com.sd.energy.domain.model.User;
import com.sd.energy.security.service.JwtTokenService;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

import org.springframework.data.util.Pair;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtEncoder jwtEncoder;
    private final SecureUserMapper secureUserMapper;

    @Override
    public Pair<UserDto, String> generateTokenForUser(User user) {
        Instant now = Instant.now();

        var claims = JwtClaimsSet.builder()
                                 .issuedAt(now)
                                 .expiresAt(now.plusSeconds(60 * 60))
                                 .subject(user.getUsername())
                                 .claim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(joining(" ")))
                                 .build();

        return Pair.of(secureUserMapper.toUserDto(user), jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    }
}
