package org.backend.service;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.backend.dto.userDto.UserRequestDto;
import org.backend.dto.userDto.UserResponseDto;
import org.backend.entity.ConfirmationCode;
import org.backend.entity.User;
import org.backend.repository.ConfirmationCodeRepository;
import org.backend.repository.UserRepository;
import org.backend.service.exception.AlreadyExistException;
import org.backend.service.exception.NotFoundException;
import org.backend.service.mail.MailUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService{

    private final UserRepository userRepository;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final MailUtil mailUtil;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public UserResponseDto registration(UserRequestDto newUser) {

        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new AlreadyExistException("User with email: "
                            + newUser.getEmail() + " already registered");
        }


        User user = User.builder()

                .email(newUser.getEmail())
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .hashPassword(passwordEncoder.encode(newUser.getHashPassword()))
                .state(User.State.CONFIRMED)
                .role(User.Role.USER)
                .build();

        userRepository.save(user);

        String code = UUID.randomUUID().toString();

        saveConfirmCode(user, code);

        // отправка кода по электронной почте

        //sendEmail(user,code);

        return UserResponseDto.from(user);

    }

    private void sendEmail(User user, String code) {
        String link = "http://localhost:8080/api/public/confirm?code=" + code;
//        log.info("ссылка для отправки email: {}", link);
        mailUtil.send(
                user.getFirstName(),
                user.getLastName(),
                link,
                "Code confirmation email",
                user.getEmail());
    }

    private void saveConfirmCode(User newUser, String codeUUID) {
        ConfirmationCode confirmationCode = ConfirmationCode.builder()
                .code(codeUUID)
                .user(newUser)
                .expiredDateTime(LocalDateTime.now().plusDays(1))
                .build();

        confirmationCodeRepository.save(confirmationCode);
    }


    @Transactional
    public UserResponseDto confirmation(String confirmCode) {

        ConfirmationCode code = confirmationCodeRepository
                .findByCodeAndExpiredDateTimeAfter(confirmCode, LocalDateTime.now())
                .orElseThrow(() -> new NotFoundException(
                        "Verification code not found or expired"));

        code.setConfirmed(true);
        confirmationCodeRepository.save(code);

        User user = code.getUser();
        user.setState(User.State.CONFIRMED);
        userRepository.save(user);

        return UserResponseDto.from(user);

    }


    public List<UserResponseDto> findAll() {
        return UserResponseDto.from(userRepository.findAll());
    }

    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with ID "
                        + userId + " not found"));
        return UserResponseDto.from(user);
    }

    public UserResponseDto getUserByEmail(String email ) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with ID "
                        + email + " not found"));
        return UserResponseDto.from(user);
    }


    @Transactional
    public UserResponseDto makeUserBanned(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email "
                        + email + " not found"));

        user.setState(User.State.BANNED);
        userRepository.save(user);

        return UserResponseDto.from(user);
    }

    public List<UserResponseDto> findAllFull() {
        return UserResponseDto.from(userRepository.findAll());
    }

    public UserResponseDto updateUser(String userEmail, UserRequestDto updateRequest) {
        // Проверяем, существует ли пользователь с данным id
        User existingUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("User with ID " + userEmail + " not found"));

        // Обновляем поля пользователя
        existingUser.setFirstName(updateRequest.getFirstName() != null ? updateRequest.getFirstName() : existingUser.getFirstName());
        existingUser.setLastName(updateRequest.getLastName() != null ? updateRequest.getLastName() : existingUser.getLastName());
        existingUser.setEmail(updateRequest.getEmail() != null ? updateRequest.getEmail() : existingUser.getEmail());
        existingUser.setHashPassword(updateRequest.getHashPassword() != null ? updateRequest.getHashPassword() : existingUser.getHashPassword());

        // Сохраняем обновленные данные пользователя в базу данных
        User updatedUser = userRepository.save(existingUser);

        // Возвращаем обновленные данные пользователя в виде DTO
        return UserResponseDto.from(updatedUser);
    }

    @SneakyThrows
    public UserResponseDto updateUserRole(Long id, String role) {
        // Найти пользователя по ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));

        // Проверить, является ли переданная роль допустимой
        User.Role newRole;
        try {
            newRole = User.Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid role: " + role);
        }

        // Установить новую роль пользователю
        existingUser.setRole(newRole);

        // Сохранить изменения в базе данных
        User updatedUser = userRepository.save(existingUser);

        // Вернуть обновленные данные пользователя в виде DTO
        return UserResponseDto.from(updatedUser);
    }



    public List<ConfirmationCode> findCodesByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email "
                        + email + " not found"));

        return confirmationCodeRepository.findByUser(user);
    }



    public void deleteUserById(Long userId) {
        // Удаление пользователя по id
         userRepository.deleteById(userId);
    }


}






