package org.backend.controller;





import lombok.RequiredArgsConstructor;
import org.backend.controller.api.AdminApi;
import org.backend.dto.tourDto.TourRequestDto;
import org.backend.dto.tourDto.TourResponseDto;
import org.backend.dto.userDto.UserResponseDto;
import org.backend.entity.ConfirmationCode;
import org.backend.service.TourService;
import org.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final UserService service;
    private final TourService tourService;

    @Override
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<List<UserResponseDto>> deleteUser(Long userId) { service.deleteUserById(userId);
        List<UserResponseDto> remainingUsers = service.findAll();
        // Обновленный список пользователей
         return ResponseEntity.ok(remainingUsers);
             }

    @Override
    public ResponseEntity<UserResponseDto> makeUserBan(String email) {
        return ResponseEntity.ok(service.makeUserBanned(email));
    }

    @Override
    public ResponseEntity<List<UserResponseDto>> findAllFull() {
        return ResponseEntity.ok(service.findAllFull());
    }

    @Override
    public ResponseEntity<List<ConfirmationCode>> findAllCodes(String email) {
        return ResponseEntity.ok(service.findCodesByUser(email));
    }

    @Override
    public ResponseEntity<TourResponseDto> updateTour(Long id, TourRequestDto updateRequest) {
        TourResponseDto updatedTour = tourService.updateTour(id, updateRequest);
        return ResponseEntity.ok(updatedTour);
    }


}

