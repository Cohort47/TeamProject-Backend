package org.backend.controller;



import lombok.RequiredArgsConstructor;
import org.backend.controller.api.UserApi;

import org.backend.dto.userDto.UserRequestDto;
import org.backend.dto.userDto.UserResponseDto;
import org.backend.service.BookingService;
import org.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;


    @Override
    public ResponseEntity<UserResponseDto> getUserById(long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUser(UserRequestDto updateRequest) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserResponseDto updatedUser = userService.updateUser(userEmail, updateRequest);
        return ResponseEntity.ok(updatedUser);
    }

}
