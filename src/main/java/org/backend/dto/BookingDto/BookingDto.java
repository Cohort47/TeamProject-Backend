package org.backend.dto.BookingDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Booking;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private Long id;
    private Long userId;
    private Long tourId;
    private String state;

    public static BookingDto from(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .tourId(booking.getTourId())
                .state(booking.getState().toString())
                .build();

    }
        public static List<BookingDto> from (List <Booking> bookings) {
            return bookings.stream()
                    .map(booking -> BookingDto.from(booking))
                    .toList();
        }

    }

