package com.sd.energy.security.controller;

import com.sd.energy.domain.dto.AuthRequest;
import com.sd.energy.domain.dto.RegisterUserRequest;
import com.sd.energy.domain.dto.UserDto;
import com.sd.energy.domain.model.User;
import com.sd.energy.security.service.AuthUserService;
import com.sd.energy.security.service.JwtTokenService;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> doRegister(@RequestBody @Valid AuthRequest authRequest) {
        try {
            Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));

            Pair<UserDto, String> userDtoStringPair = jwtTokenService.generateTokenForUser((User) authentication.getPrincipal());

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, userDtoStringPair.getSecond())
                                 .body(userDtoStringPair.getFirst());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> doRegister(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        try {
            return ResponseEntity.ok(authUserService.registerUser(registerUserRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
