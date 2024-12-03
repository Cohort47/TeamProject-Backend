package org.backend.controller;



import lombok.RequiredArgsConstructor;
import org.backend.controller.api.UserApi;

import org.backend.dto.userDto.UserResponseDto;
import org.backend.service.BookingService;
import org.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService service;
    private final BookingService bookingService;


    @Override
    public ResponseEntity<UserResponseDto> getUserById(long userId) {
        return ResponseEntity.ok(service.getUserById(userId));
    }


//    @Override
//    public ResponseEntity<List<BookingResponseDto>> getBookingsByUser(Long userId) {
//        List<BookingResponseDto> bookings = bookingService.getBookingsByUser(userId);
//        return ResponseEntity.ok(bookings);
//    }
}
