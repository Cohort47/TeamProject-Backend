package org.backend.controller;



import lombok.RequiredArgsConstructor;
import org.backend.controller.api.UserApi;

import org.backend.dto.userDto.UserDto;
import org.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService service;


    @Override
    public ResponseEntity<UserDto> getUserById(long userId) {
        return ResponseEntity.ok(service.getUserById(userId));
    }
}
