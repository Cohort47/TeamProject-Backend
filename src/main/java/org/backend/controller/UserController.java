package org.backend.controller;



import lombok.RequiredArgsConstructor;
import org.backend.controller.api.UserApi;

import org.backend.dto.userDto.UserRequestDto;
import org.backend.dto.userDto.UserResponseDto;
import org.backend.service.BookingService;
import org.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
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



    @DeleteMapping("/{userId}")
    public ResponseEntity<List<UserResponseDto>> deleteUser(@PathVariable Long userId, @RequestParam boolean logicalDelete) {
            try {
                userService.deleteUserById(userId, logicalDelete);
                List<UserResponseDto> remainingUsers = userService.findAll();
                // Обновляю список пользователей
                return ResponseEntity.ok(remainingUsers);
            } catch (ResponseStatusException e) {
                // Возвращаю понятное сообщение об ошибке, если удаление не выполнено
                return ResponseEntity.status(e.getStatusCode())
                        .body(Collections.singletonList(new UserResponseDto().setErrorMessage(e.getReason())));
            }
        }

    }


