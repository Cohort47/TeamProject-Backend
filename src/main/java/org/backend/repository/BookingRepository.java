package org.backend.repository;

import org.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findById(Long id);
    boolean existsById(Long id);



    Optional<Booking> findBookingByState(String bookingState);
}
