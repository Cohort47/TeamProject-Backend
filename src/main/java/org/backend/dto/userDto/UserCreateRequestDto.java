package org.backend.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDto {

    @NotBlank(message = "User name should not be empty")
    @Size(min = 2, max = 15)
    private String userFirstName;

    @NotBlank(message = "User name should not be empty")
    @Size(min = 2, max = 15)
    private String userLastName;

    @NotBlank(message = "Password should not be empty")
    @Size(min = 8, max = 25)
    private String password;

    @Email(message = "Incorrect email format")
    private String email;
}
