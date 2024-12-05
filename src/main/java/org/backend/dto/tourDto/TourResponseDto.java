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
public class TourResponseDto {

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
    private List<String> photoLinks;

    public static TourResponseDto from(Tour tour){
        return TourResponseDto.builder()
                .id(tour.getId())
                .title(tour.getTitle())
                .description(tour.getDescription())
                .price(tour.getPrice())
                .duration(tour.getDuration())
                .startDate(tour.getStartDate())
                .endDate(tour.getEndDate())
                .state(tour.getState())
                .country(tour.getCountry())
                .city(tour.getCity())
                .photoLinks(tour.getPhotoLinks())

                .build();
    }

    public static List<TourResponseDto> from(List<Tour> tours){
        return tours.stream()
                .map(tour -> TourResponseDto.from(tour))
                .toList();
    }
}
