package org.backend.dto.tourDto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Tour;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourDto {

    private Long id;
    private String title;
    private String description;
    private Long price;
    private Long duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private Tour.State state;
    private String country;
    private String city;

    public static TourDto from(Tour tour){
        return TourDto.builder()
                .id(tour.getId())
                .title(tour.getTitle())
                .description(tour.getDescription())
                .price(tour.getPrice())
                .duration(tour.getDuration())
                .startDate(tour.getStartDate())
                .startDate(tour.getEndDate())
                .state(tour.getState())
                .country(tour.getCountry())
                .city(tour.getCity())

                .build();
    }

    public static List<TourDto> from(List<Tour> tours){
        return tours.stream()
                .map(tour -> TourDto.from(tour))
                .toList();
    }
}
