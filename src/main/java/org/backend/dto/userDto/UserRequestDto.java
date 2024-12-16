package org.backend.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank (message = "User firstName should not be empty")
    private String firstName;

    @NotBlank (message = "User lastName should not be empty")
    private String lastName;

    @Email (message = "Incorrect email format")
    @NotBlank (message = "User email should not be empty")
    private String email;

    @NotBlank (message = "User password should not be empty")
    private String password;

    private String photoLinks;

}
