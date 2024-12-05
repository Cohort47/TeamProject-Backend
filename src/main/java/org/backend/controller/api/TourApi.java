package org.backend.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.backend.dto.responseDto.ErrorResponseDto;
import org.backend.dto.tourDto.TourResponseDto;
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


    @GetMapping("/all")
    ResponseEntity<List<TourResponseDto>> findAll();

    @Operation(summary = "Getting information about the Tour by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tour information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TourResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Tour not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/{tourId}")
    ResponseEntity<TourResponseDto> getTourById(@PathVariable long tourId);

    @GetMapping
    ResponseEntity<List<TourResponseDto>> getTours(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) Long price,
            @RequestParam(required = false) Long duration,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city
    );
}

