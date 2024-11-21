package org.backend.dto.BookingDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Booking;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    private Long id;
    private Long userId;
    private Long tourId;
    private Booking.State state;

}
