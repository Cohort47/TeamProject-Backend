package org.backend.controller;


import lombok.RequiredArgsConstructor;
import org.backend.controller.api.BookingApi;
import org.backend.dto.BookingDto.BookingDto;
import org.backend.entity.Booking;
import org.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class BookingController implements BookingApi {

    private final BookingService bookingService;


    @Override
    public ResponseEntity<Booking> createBooking(@RequestParam Long userId, @RequestParam Long tourId, @RequestParam LocalDate tourDate) {
        Booking booking = bookingService.createBooking(userId, tourId, tourDate);
        return ResponseEntity.ok(booking);
    }

    @Override
    public ResponseEntity<Booking> updateBookingState(@PathVariable Long bookingId, @RequestParam Booking.State newState) {
        Booking updatedBooking = bookingService.updateBookingState(bookingId, newState);
        return ResponseEntity.ok(updatedBooking); }

    @Override
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build(); }

    @Override
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long bookingId) {
        BookingDto booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking); }


    @Override
    public ResponseEntity<List<BookingDto>> findAll() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    @Override
    public ResponseEntity<List<BookingDto>> findBookingByState(@PathVariable Booking.State state) {
        List<BookingDto> bookings = bookingService.findBookingByState(state);
        return ResponseEntity.ok(bookings);
    }

    @Override
    public ResponseEntity<List<BookingDto>> getBookingsByBookingDate(LocalDate bookingDate) {
        List<BookingDto> bookings = bookingService.getBookingsByBookingDate(bookingDate);
        return ResponseEntity.ok(bookings);
    }

    @Override
    public ResponseEntity<List<BookingDto>> getBookingsByTourDate(LocalDate tourDate) {
        List<BookingDto> bookings = bookingService.getBookingsByTourDate(tourDate);
        return ResponseEntity.ok(bookings);
    }



}