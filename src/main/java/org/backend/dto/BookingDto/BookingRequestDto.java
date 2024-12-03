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


    @NotBlank(message = "Tour ID not be empty")
    private Long tourId;


    @NotBlank(message = "Booking tourDate not be empty")
    private LocalDate tourDate; // Дата начала тура

    @NotBlank(message = "Booking amountOfPeople not be empty")
    private Integer amountOfPeople;

}
