package org.backend.controller.api;



import org.backend.dto.tourDto.TourRequestDto;
import org.backend.dto.tourDto.TourResponseDto;
import org.backend.dto.userDto.UserResponseDto;
import org.backend.entity.ConfirmationCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
public interface AdminApi {

    @GetMapping("/users")
    ResponseEntity<List<UserResponseDto>> findAll();

    @DeleteMapping("/users/{userId}")
    ResponseEntity<List<UserResponseDto>> deleteUser(@PathVariable Long userId);

    @GetMapping("/ban")
    ResponseEntity<UserResponseDto> makeUserBan(@RequestParam String email);


    @GetMapping("/users/full-details")
    ResponseEntity<List<UserResponseDto>> findAllFull();

    @GetMapping("/users/all-codes")
    ResponseEntity<List<ConfirmationCode>> findAllCodes(@RequestParam String email);


    //Обновление тура
    @PutMapping("/tours/{id}")
    ResponseEntity<TourResponseDto> updateTour(@PathVariable Long id, @RequestBody TourRequestDto updateRequest);


}
