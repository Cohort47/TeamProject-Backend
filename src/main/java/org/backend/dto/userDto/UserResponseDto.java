package org.backend.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.entity.User;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;


    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }
    public static List<UserResponseDto> from(List<User> users){
        return users.stream()
                .map(user -> UserResponseDto.from(user))
                .toList();
    }

}
