package org.backend.repository;


import org.backend.entity.ConfirmationCode;
import org.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {
    Optional<ConfirmationCode> findByCodeAndExpiredDateTimeAfter(String code, LocalDateTime currentTime);

    List<ConfirmationCode> findByUser(User user);
}
