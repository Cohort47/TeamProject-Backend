package org.backend.controller.api;



import org.backend.dto.userDto.UserDto;
import org.backend.entity.ConfirmationCode;
import org.backend.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
public interface AdminApi {

    @GetMapping("/users")
    ResponseEntity<List<UserDto>> findAll();

    @DeleteMapping("/users/{userId}")
    ResponseEntity<List<UserDto>> deleteUser(@PathVariable Long userId);

    @GetMapping("/bann")
    ResponseEntity<UserDto> makeUserBan(@RequestParam String email);


    @GetMapping("/users/fullDetails")
    ResponseEntity<List<User>> findAllFull();

    @GetMapping("/users/allCodes")
    ResponseEntity<List<ConfirmationCode>> findAllCodes(@RequestParam String email);


}
