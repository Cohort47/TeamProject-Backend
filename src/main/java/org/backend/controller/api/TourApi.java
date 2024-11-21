package org.backend.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.backend.dto.responseDto.ErrorResponseDto;
import org.backend.dto.tourDto.TourDto;
import org.backend.entity.Tour;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/tours")
public interface TourApi {

    ResponseEntity<List<TourDto>> findAll();

    ResponseEntity<List<Tour>> findAllFull();

    @Operation(summary = "Getting information about the Tour ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tour information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TourDto.class))),
            @ApiResponse(responseCode = "404", description = "Tour not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)))
    }
    )
    @GetMapping("/{tourId}")
    public ResponseEntity<TourDto> getTourById(@PathVariable long tourId);

    @GetMapping("/{tourTitle}")
    public ResponseEntity<TourDto> getTourByTitle(@PathVariable String tourTitle);

    @GetMapping("/{tourState}")
    public ResponseEntity<TourDto> getTourByState(@PathVariable String tourState);

    @GetMapping("/{tourPrice}")
    public ResponseEntity<TourDto> getTourByPrice(@PathVariable long tourPrice);

    @GetMapping("/{tourDuration}")
    public ResponseEntity<TourDto> getTourByDuration(@PathVariable long tourDuration);

    @GetMapping("/{tourStartDate}")
    public ResponseEntity<TourDto> getTourByStartDate(@PathVariable LocalDate tourStartDate );

    @GetMapping("/{tourEndDate}")
    public ResponseEntity<TourDto> getTourByEndDate(@PathVariable LocalDate tourEndDate);

    // можно добавить updateTour
    /*
    @PutMapping("/{tourId}")

     */
}
