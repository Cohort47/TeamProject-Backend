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

    private final UserService userService;
    private final TourService tourService;

    @Override
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<List<UserResponseDto>> deleteUser(Long userId) {
        userService.deleteUserById(userId);
        List<UserResponseDto> remainingUsers = userService.findAll();
        // Обновленный список пользователей
         return ResponseEntity.ok(remainingUsers);
    }

    @Override
    public ResponseEntity<UserResponseDto> makeUserBan(String email) {
        return ResponseEntity.ok(userService.makeUserBanned(email));
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUserRole(Long id, String role) {
        UserResponseDto updatedUserRole = userService.updateUserRole(id, role);
        return ResponseEntity.ok(updatedUserRole);
    }


    @Override
    public ResponseEntity<List<ConfirmationCode>> findAllCodes(String email) {
        return ResponseEntity.ok(userService.findCodesByUser(email));
    }

    @Override
    public ResponseEntity<TourResponseDto> updateTour(Long id, TourRequestDto updateRequest) {
        TourResponseDto updatedTour = tourService.updateTour(id, updateRequest);
        return ResponseEntity.ok(updatedTour);
    }

    public ResponseEntity<List<TourResponseDto>> deleteTour(Long id) {
        tourService.deleteTour(id);

        List<TourResponseDto> remainingTours = tourService.findAll();

        return ResponseEntity.ok(remainingTours);
    }


}

