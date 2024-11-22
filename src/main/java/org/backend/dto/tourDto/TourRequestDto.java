package org.backend.dto.tourDto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Tour;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourRequestDto {

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "description is required")
    private String description;

    @NotBlank(message = "price is required")
    private Long price;

    @NotBlank(message = "duration is required")
    private Long duration;

    @NotBlank(message = "startDate is required")
    private LocalDate startDate;

    @NotBlank(message = "endDate is required")
    private LocalDate endDate;

    @NotBlank(message = "Tour.State is required")
    private Tour.State state;

    @NotBlank(message = "country is required")
    private String country;

    @NotBlank(message = "city is required")
    private String city;

    private String imageUrl;
}


