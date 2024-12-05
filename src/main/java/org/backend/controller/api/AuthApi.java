package org.backend.controller.api;

import jakarta.validation.Valid;
import org.backend.dto.userDto.UserResponseDto;
import org.backend.security.dto.AuthRequest;
import org.backend.security.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public interface AuthApi {

    @PostMapping
    ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request);

    @GetMapping("/me")
    ResponseEntity<UserResponseDto> authenticate();


}
