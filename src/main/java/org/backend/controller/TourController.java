package org.backend.controller;


import lombok.RequiredArgsConstructor;
import org.backend.controller.api.TourApi;
import org.backend.dto.tourDto.TourDto;
import org.backend.entity.Tour;
import org.backend.service.TourService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class TourController implements TourApi {

    private final TourService tourService;


    @Override
    public ResponseEntity<List<TourDto>> findAll() {
        return ResponseEntity.ok(tourService.findAll());
    }

    @Override
    public ResponseEntity<List<Tour>> findAllFull() {
        return ResponseEntity.ok(tourService.findAllFull());
    }

    @Override
    public ResponseEntity<TourDto> getTourById(@PathVariable long tourId) {
        return ResponseEntity.ok(tourService.getTourById(tourId));
    }

    @Override
    public ResponseEntity<List<TourDto>> getToursByTitle(@PathVariable String title) {
        return ResponseEntity.ok(tourService.getToursByTitle(title));
    }

    @Override
    public ResponseEntity<List<TourDto>> getToursByState(@PathVariable String tourState) {
        return ResponseEntity.ok(tourService.getToursByState(tourState));
    }

    @Override
    public ResponseEntity<List<TourDto>> getToursByPrice(@PathVariable long price) {
        return ResponseEntity.ok(tourService.getToursByPrice(price));
    }

    @Override
    public ResponseEntity<List<TourDto>> getToursByDuration(@PathVariable long duration) {
        return ResponseEntity.ok(tourService.getToursByDuration(duration));
    }

    @Override
    public ResponseEntity<List<TourDto>> getToursByStartDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        return ResponseEntity.ok(tourService.getToursByStartDate(startDate));
    }

    @Override
    public ResponseEntity<List<TourDto>> getToursByEndDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(tourService.getToursByEndDate(endDate));
    }

    @Override
    public ResponseEntity<List<TourDto>> getToursByCountry(@PathVariable String country) {
        return ResponseEntity.ok(tourService.getToursByCountry(country));
    }

    @Override
    public ResponseEntity<List<TourDto>> getToursByCity(@PathVariable String city) {
        return ResponseEntity.ok(tourService.getToursByCity(city));
    }
}