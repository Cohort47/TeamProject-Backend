package org.backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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






