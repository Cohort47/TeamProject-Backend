package org.backend.controller;

import lombok.RequiredArgsConstructor;
import org.backend.controller.api.AuthApi;
import org.backend.dto.userDto.UserResponseDto;
import org.backend.security.dto.AuthRequest;
import org.backend.security.dto.AuthResponse;
import org.backend.security.service.JwtTokenProvider;
import org.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class
AuthController implements AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;


    @Override
    public ResponseEntity<AuthResponse> authenticate(AuthRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(request.getEmail());

        return new ResponseEntity<>(new AuthResponse(jwt), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> authenticate() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        UserResponseDto user = userService.getUserByEmail(email);

        return ResponseEntity.ok(user);
    }
}
