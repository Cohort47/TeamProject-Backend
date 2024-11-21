package org.backend.dto.BookingDto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Booking;

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
}
