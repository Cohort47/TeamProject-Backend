package org.backend.controller;



import lombok.RequiredArgsConstructor;
import org.backend.controller.api.PublicApi;
import org.backend.dto.userDto.UserRequestDto;
import org.backend.dto.userDto.UserResponseDto;
import org.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublicController implements PublicApi {

    private final UserService service;


    @Override
    public ResponseEntity<UserResponseDto> userRegistration(UserRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.registration(request));
    }

    @Override
    public ResponseEntity<UserResponseDto> confirmRegistration(String code) {
        return ResponseEntity.ok(service.confirmation(code));
    }
}
