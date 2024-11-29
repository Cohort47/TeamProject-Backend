package org.backend.repository;

import org.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findById(Long id);
    boolean existsById(Long id);



    Optional<Booking> findBookingByState(Booking.State state);



    Optional<Booking> findByDuration(long bookingDuration);
    boolean existsByDuration(long bookingDuration);



    Optional<Booking> findByStartDate(LocalDate bookingStartDate);

    Optional<Booking> findByEndDate(LocalDate bookingEndDate);
}
