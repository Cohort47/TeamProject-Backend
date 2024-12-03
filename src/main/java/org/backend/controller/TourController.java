package org.backend.controller;


import lombok.RequiredArgsConstructor;
import org.backend.controller.api.TourApi;
import org.backend.dto.tourDto.TourResponseDto;
import org.backend.service.TourService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class TourController implements TourApi {

    private final TourService tourService;


    @Override
    public ResponseEntity<List<TourResponseDto>> findAll() {
        return ResponseEntity.ok(tourService.findAll());
    }

    @Override
    public ResponseEntity<TourResponseDto> getTourById(@PathVariable long tourId) {
        return ResponseEntity.ok(tourService.getTourById(tourId));
    }

    @Override
    public ResponseEntity<List<TourResponseDto>> getTours(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) Long price,
            @RequestParam(required = false) Long duration,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city) {

        List<TourResponseDto> tours = tourService.searchTours(title, state, price, duration, startDate, endDate, country, city);
        if (tours.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tours);
    }
}