package org.backend.dto.tourDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Tour;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourResponseDto {

    private Long id;
    private String title;
    private String description;
    private Long price;
    private Long duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private Tour.State state;
}
