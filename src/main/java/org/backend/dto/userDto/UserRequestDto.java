package org.backend.dto.userDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "User firstName is required")
    private String userFirstName;

    @NotBlank(message = "User lastName is required")
    private String userLastName;

    @NotBlank(message = "User email is required")
    private String email;
}
