package org.backend.controller;



import lombok.RequiredArgsConstructor;
import org.backend.controller.api.UserApi;

import org.backend.dto.BookingDto.BookingDto;
import org.backend.dto.userDto.UserDto;
import org.backend.service.BookingService;
import org.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService service;
    private final BookingService bookingService;


    @Override
    public ResponseEntity<UserDto> getUserById(long userId) {
        return ResponseEntity.ok(service.getUserById(userId));
    }


    @Override
    public ResponseEntity<List<BookingDto>> getBookingsByUser(Long userId) {
        List<BookingDto> bookings = bookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(bookings);
    }
}
