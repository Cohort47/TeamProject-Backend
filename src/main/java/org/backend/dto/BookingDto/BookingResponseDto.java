package org.backend.dto.BookingDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Booking;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    private Long id;
    private Long userId;
    private Long tourId;
    private LocalDate bookingDate; // Дата создания бронирования
    private LocalDate tourDate; // Дата начала тура
    private Booking.State state;

}
