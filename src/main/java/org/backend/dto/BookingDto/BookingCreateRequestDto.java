package org.backend.dto.BookingDto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Booking;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreateRequestDto {

    @NotBlank(message = "User ID not be empty")
    private Long userId;

    @NotBlank(message = "Tour ID not be empty")
    private Long tourId;

    @NotBlank(message = "Tour State not be empty")
    private Booking.State state;

    @NotBlank(message = "Booking duration not be empty")
    private Long duration;

    @NotBlank(message = "Booking startDate not be empty")
    private LocalDate startDate;

    @NotBlank(message = "Booking endDate not be empty")
    private LocalDate endDate;
}
