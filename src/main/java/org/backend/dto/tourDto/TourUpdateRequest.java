package org.backend.dto.tourDto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TourUpdateRequest {
    private String title;
    private String description;
    private Long price;
    private Long duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private String country;
    private String city;
    private String state; // "AVAILABLE" или "CLOSED"
    private List<String> photoLinks;
}