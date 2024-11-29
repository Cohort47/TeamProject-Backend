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
public class BookingRequestDto {

    @NotBlank(message = "UserId is required")
    private Long userId;

    @NotBlank(message = "TourId is required")
    private String tourId;

    @NotBlank(message = "Booking.State is required")
    private Booking.State state;

    @NotBlank(message = "Booking duration is required")
    private Long duration;

    @NotBlank(message = "Booking startDate is required")
    private LocalDate startDate;

    @NotBlank(message = "Booking endDate is required")
    private LocalDate endDate;
}
