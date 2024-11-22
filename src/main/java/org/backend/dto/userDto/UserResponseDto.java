package org.backend.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.Role;
import org.backend.entity.User;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String userFirstName;
    private String userLastName;
    private String email;
    private Collection<Role> roles;
}
