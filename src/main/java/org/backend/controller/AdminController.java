package org.backend.controller;




import lombok.RequiredArgsConstructor;
import org.backend.controller.api.AdminApi;
import org.backend.dto.userDto.UserDto;
import org.backend.entity.ConfirmationCode;
import org.backend.entity.User;
import org.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final UserService service;

    @Override
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<List<UserDto>> deleteUser(Long userId) { service.deleteUserById(userId);
        List<UserDto> remainingUsers = service.findAll();
        // Обновленный список пользователей
         return ResponseEntity.ok(remainingUsers);
    }

    @Override
    public ResponseEntity<UserDto> makeUserBan(String email) {
        return ResponseEntity.ok(service.makeUserBanned(email));
    }

    @Override
    public ResponseEntity<List<User>> findAllFull() {
        return ResponseEntity.ok(service.findAllFull());
    }

    @Override
    public ResponseEntity<List<ConfirmationCode>> findAllCodes(String email) {
        return ResponseEntity.ok(service.findCodesByUser(email));
    }


}

