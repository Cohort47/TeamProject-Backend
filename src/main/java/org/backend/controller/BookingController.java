package org.backend.controller;


import org.backend.controller.api.BookingApi;
import org.backend.dto.BookingDto.BookingDto;
import org.backend.entity.Booking;
import org.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController implements BookingApi {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService; }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam Long userId, @RequestParam Long tourId) {
        Booking booking = bookingService.createBooking(userId, tourId);
        return ResponseEntity.ok(booking); }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBookingState(@PathVariable Long bookingId, @RequestParam Booking.State newState) {
        Booking updatedBooking = bookingService.updateBookingState(bookingId, newState);
        return ResponseEntity.ok(updatedBooking); }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build(); }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBooking(bookingId);
        return ResponseEntity.ok(booking); }


    @Override
    public ResponseEntity<List<BookingDto>> findAll() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    @Override
    public ResponseEntity<List<Booking>> findAllFull() {
        return ResponseEntity.ok(bookingService.findAllFull());
    }

    @Override
    public ResponseEntity<BookingDto> getBookingById(long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }
}
