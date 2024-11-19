package org.backend.controller;


import lombok.RequiredArgsConstructor;
import org.backend.controller.api.TourApi;

import org.backend.dto.tourDto.TourDto;
import org.backend.entity.Tour;
import org.backend.service.TourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class TourController implements TourApi {


    private final TourService service;

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
    public ResponseEntity<TourDto> getTourByTitle(String tourTitle) {
        return ResponseEntity.ok(service.getTourByTitle(tourTitle));
    }

    @Override
    public ResponseEntity<TourDto> getTourByState(String tourState) {
        return ResponseEntity.ok(service.getTourByState(tourState));
    }

    @Override
    public ResponseEntity<TourDto> getTourByPrice(long tourPrice) {
        return ResponseEntity.ok(service.getTourByPrice(tourPrice));
    }

    @Override
    public ResponseEntity<TourDto> getTourByDuration(long tourDuration) {
        return ResponseEntity.ok(service.getTourByDuration(tourDuration));
    }

    @Override
    public ResponseEntity<TourDto> getTourByStartDate(LocalDate tourStartDate) {
        return ResponseEntity.ok(service.getTourByStartDate(tourStartDate));
    }

    @Override
    public ResponseEntity<TourDto> getTourByEndDate(LocalDate tourEndDate) {
        return ResponseEntity.ok(service.getTourByEndDate(tourEndDate));
    }
}
