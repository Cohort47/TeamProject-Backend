package org.backend.controller;


import lombok.RequiredArgsConstructor;
import org.backend.controller.api.TourApi;
import org.backend.dto.tourDto.TourDto;
import org.backend.entity.Tour;
import org.backend.service.TourService;
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


    private final TourService service;
    private final TourService tourService;

    @Override
    public ResponseEntity<List<TourDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<List<Tour>> findAllFull() {
        return ResponseEntity.ok(service.findAllFull());
    }

    @Override
    public ResponseEntity<TourDto> getTourById(long tourId) {
        return ResponseEntity.ok(service.getTourById(tourId));
    }

    @Override
    public ResponseEntity<TourDto> getTourByTitle(String title) {
        return ResponseEntity.ok(service.getTourByTitle(title));
    }

    @Override
    public ResponseEntity<TourDto> getTourByState(String tourState) {
        return ResponseEntity.ok(service.getTourByState(tourState));
    }

    @Override
    public ResponseEntity<TourDto> getTourByPrice(long price) {
        return ResponseEntity.ok(service.getTourByPrice(price));
    }

    @Override
    public ResponseEntity<TourDto> getTourByDuration(long duration) {
        return ResponseEntity.ok(service.getTourByDuration(duration));
    }

    @Override
    public ResponseEntity<TourDto> getTourByStartDate(LocalDate startDate) {
        return ResponseEntity.ok(service.getTourByStartDate(startDate));
    }

    @Override
    public ResponseEntity<TourDto> getTourByEndDate(LocalDate endDate) {
        return ResponseEntity.ok(service.getTourByEndDate(endDate));
    }

    @PutMapping("/{tourId}")
    public ResponseEntity<Tour> updateTourState(@PathVariable Long tourId, @RequestParam Tour.State newState) {
        Tour updatedTour = tourService.updateTourState(tourId, newState);
        return ResponseEntity.ok(updatedTour); }

    @Override
    public ResponseEntity<TourDto> getTourByCountry(String country) {
        return ResponseEntity.ok(service.getTourByCountry(country));
    }

    @Override
    public ResponseEntity<TourDto> getTourByCity(String city) {
        return ResponseEntity.ok(service.getTourByCity(city));
    }
}
