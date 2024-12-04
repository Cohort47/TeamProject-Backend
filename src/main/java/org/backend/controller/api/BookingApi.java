package org.backend.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.backend.dto.BookingDto.BookingRequestDto;
import org.backend.dto.BookingDto.BookingResponseDto;
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
                            schema = @Schema(implementation = BookingResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)))
    }
    )

    @PostMapping
    ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto request);

    @PutMapping("/{bookingId}")
    ResponseEntity<BookingResponseDto> updateBookingState(@PathVariable Long bookingId, @RequestParam Booking.State newState);

    @DeleteMapping("/{bookingId}")
    ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId);

    @GetMapping("/{bookingId}")
    ResponseEntity<BookingResponseDto> getBookingById(@PathVariable Long bookingId);

    //TODO Удалить
    @GetMapping("/all")
    ResponseEntity<List<BookingResponseDto>> findAll();

    @GetMapping()
    ResponseEntity<List<BookingResponseDto>>  getBookings(
            @RequestParam(required = false) Booking.State state,
            @RequestParam(required = false) LocalDate bookingDate,
            @RequestParam(required = false) LocalDate tourDate,
            @RequestParam(required = false) Long tourId,
            @RequestParam(required = false) Integer amountOfPeople
    );

}