package org.backend.dto.BookingDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Booking;


import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private Long id;
    private Long userId;
    private Long tourId;
    private LocalDate bookingDate; // Дата создания бронирования
    private LocalDate tourDate; // Дата начала тура
    private Booking.State state;

    public static BookingDto from(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .userId(booking.getUser().getId())
                .tourId(booking.getTour().getId())
                .bookingDate(booking.getBookingDate())
                .tourDate(booking.getTourDate())
                .state(booking.getState())
                .build();

    }
        public static List<BookingDto> from (List <Booking> bookings) {
            return bookings.stream()
                    .map(booking -> BookingDto.from(booking))
                    .toList();
        }

    }

