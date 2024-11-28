package org.backend.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.backend.dto.responseDto.ErrorResponseDto;
import org.backend.dto.tourDto.TourDto;
import org.backend.entity.Tour;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/tours")
public interface TourApi {

    @GetMapping
    ResponseEntity<List<TourDto>> findAll();

    @GetMapping("/full")
    ResponseEntity<List<Tour>> findAllFull();

    @Operation(summary = "Getting information about the Tour by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tour information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TourDto.class))),
            @ApiResponse(responseCode = "404", description = "Tour not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/{tourId}")
    ResponseEntity<TourDto> getTourById(@PathVariable long tourId);

    @GetMapping("/title/{title}")
    ResponseEntity<List<TourDto>> getToursByTitle(@PathVariable String title);

    @GetMapping("/state/{tourState}")
    ResponseEntity<List<TourDto>> getToursByState(@PathVariable String tourState);

    @GetMapping("/price/{price}")
    ResponseEntity<List<TourDto>> getToursByPrice(@PathVariable long price);

    @GetMapping("/duration/{duration}")
    ResponseEntity<List<TourDto>> getToursByDuration(@PathVariable long duration);

    @GetMapping("/start-date/{startDate}")
    ResponseEntity<List<TourDto>> getToursByStartDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate);

    @GetMapping("/end-date/{endDate}")
    ResponseEntity<List<TourDto>> getToursByEndDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);

    @GetMapping("/country/{country}")
    ResponseEntity<List<TourDto>> getToursByCountry(@PathVariable String country);

    @GetMapping("/city/{city}")
    ResponseEntity<List<TourDto>> getToursByCity(@PathVariable String city);
}