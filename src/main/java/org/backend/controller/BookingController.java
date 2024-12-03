package org.backend.controller;


import lombok.RequiredArgsConstructor;
import org.backend.controller.api.BookingApi;
import org.backend.dto.BookingDto.BookingRequestDto;
import org.backend.dto.BookingDto.BookingResponseDto;
import org.backend.entity.Booking;
import org.backend.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class BookingController implements BookingApi {

    private final BookingService bookingService;


    @Override
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        BookingResponseDto booking = bookingService.createBooking(email,  request.getTourId(), request.getTourDate(), request.getAmountOfPeople());
        return ResponseEntity.ok(booking);
    }


    @Override
    public ResponseEntity<BookingResponseDto> updateBookingState(@PathVariable Long bookingId, @RequestParam Booking.State newState) {
        BookingResponseDto updatedBooking = bookingService.updateBookingState(bookingId, newState);
        return ResponseEntity.ok(updatedBooking); }

    @Override
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build(); }

    @Override
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable Long bookingId) {
        BookingResponseDto booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking); }


    @Override
    public ResponseEntity<List<BookingResponseDto>> findAll() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    @Override
    public ResponseEntity<List<BookingResponseDto>> getBookings(
            @RequestParam(required = false) Booking.State state,
            @RequestParam(required = false) LocalDate bookingDate,
            @RequestParam(required = false) LocalDate tourDate,
            @RequestParam(required = false) Long tourId,
            @RequestParam(required = false) Integer amountOfPeople
    ) {
        // Получаем email авторизованного пользователя
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Получаем отфильтрованные бронирования
        List<BookingResponseDto> bookings = bookingService.getBookings(userEmail, state, bookingDate, tourDate, tourId, amountOfPeople);

        return ResponseEntity.ok(bookings);
    }
}