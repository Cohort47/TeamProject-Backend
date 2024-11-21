package org.backend.dto.BookingDto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Booking;

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
}
