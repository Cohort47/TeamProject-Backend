package org.backend.controller.api;



import org.backend.dto.tourDto.TourDto;
import org.backend.dto.tourDto.TourUpdateRequest;
import org.backend.dto.userDto.UserDto;
import org.backend.entity.ConfirmationCode;
import org.backend.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
public interface AdminApi {

    @GetMapping("/users")
    ResponseEntity<List<UserDto>> findAll();

    @DeleteMapping("/users/{userId}")
    ResponseEntity<List<UserDto>> deleteUser(@PathVariable Long userId);

    @GetMapping("/ban")
    ResponseEntity<UserDto> makeUserBan(@RequestParam String email);


    @GetMapping("/users/full-details")
    ResponseEntity<List<User>> findAllFull();

    @GetMapping("/users/all-codes")
    ResponseEntity<List<ConfirmationCode>> findAllCodes(@RequestParam String email);


    //Обновление тура
    @PutMapping("/tours/{id}")
    ResponseEntity<TourDto> updateTour(@PathVariable Long id, @RequestBody TourUpdateRequest updateRequest);


}
