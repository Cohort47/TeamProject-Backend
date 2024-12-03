package org.backend.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.backend.dto.responseDto.ErrorResponseDto;
import org.backend.dto.userDto.UserRequestDto;
import org.backend.dto.userDto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/public")
public interface PublicApi {

    @Operation(summary = "User registration", description = "operation is available to everyone, default role - USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)))
    }
    )
    @PostMapping("/register")
     ResponseEntity<UserResponseDto> userRegistration(@Valid @RequestBody UserRequestDto request);

    @GetMapping("/confirm")
    ResponseEntity<UserResponseDto> confirmRegistration(@RequestParam String code);

}
