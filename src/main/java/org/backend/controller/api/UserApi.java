package org.backend.controller.api;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.backend.dto.responseDto.ErrorResponseDto;
import org.backend.dto.userDto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/users")
public interface UserApi {

    @Operation(summary = "Getting user information by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)))
    }
    )
    @GetMapping("/{userId}")
    ResponseEntity<UserResponseDto> getUserById(@PathVariable long userId);

//    @GetMapping("/bookings/{userId}")
//    ResponseEntity<List<BookingResponseDto>> getBookingsByUser(@PathVariable Long userId);

}
