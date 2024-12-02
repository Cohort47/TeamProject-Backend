package org.backend.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.backend.dto.BookingDto.BookingDto;
import org.backend.dto.responseDto.ErrorResponseDto;
import org.backend.entity.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RequestMapping("/api/bookings")
public interface BookingApi {

    @Operation(summary = "Getting information about the Booking ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingDto.class))),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)))
    }
    )

    @PostMapping
    ResponseEntity<Booking> createBooking(@RequestParam Long userId, @RequestParam Long tourId, @RequestParam LocalDate tourDate);

    @PutMapping("/{bookingId}")
    ResponseEntity<Booking> updateBookingState(@PathVariable Long bookingId, @RequestParam Booking.State newState);

    @DeleteMapping("/{bookingId}")
    ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId);

    @GetMapping("/{bookingId}")
    ResponseEntity<BookingDto> getBookingById(@PathVariable Long bookingId);

    @GetMapping
    ResponseEntity<List<BookingDto>> findAll();

    @GetMapping("/state/{state}")
    ResponseEntity<List<BookingDto>> findBookingByState(@PathVariable Booking.State state);

    @GetMapping("/bookingDate/{bookingDate}")
    ResponseEntity<List<BookingDto>> getBookingsByBookingDate(@PathVariable LocalDate bookingDate);

    @GetMapping("/tourDate/{tourDate}")
    ResponseEntity<List<BookingDto>> getBookingsByTourDate(@PathVariable LocalDate tourDate);


}