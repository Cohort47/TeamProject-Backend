package org.backend.dto.BookingDto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.dto.tourDto.TourResponseDto;
import org.backend.dto.userDto.UserResponseDto;
import org.backend.entity.Booking;



import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseDto {

    private Long id;                   // ID бронирования
    private UserResponseDto user;      // Информация о пользователе
    private TourResponseDto tour;      // Информация о туре
    private LocalDate bookingDate;     // Дата создания бронирования
    private LocalDate tourDate;        // Дата тура
    private Integer amountOfPeople;    // Количество человек
    private Booking.State state;       // Состояние бронирования

    public static BookingResponseDto from(Booking booking) {
        return BookingResponseDto.builder()
                .id(booking.getId())
                .user(UserResponseDto.from(booking.getUser())) // Конвертация пользователя
                .tour(TourResponseDto.from(booking.getTour())) // Конвертация тура
                .bookingDate(booking.getBookingDate())
                .tourDate(booking.getTourDate())
                .amountOfPeople(booking.getAmountOfPeople())
                .state(booking.getState())
                .build();
    }

    public static List<BookingResponseDto> from (List <Booking> bookings) {
        return bookings.stream()
                .map(booking -> BookingResponseDto.from(booking))
                .toList();
    }
}
