package org.backend.repository;

import org.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findById(Long id);

    List<Booking> findBookingByState(Booking.State state);

    List<Booking> findByTourDate(LocalDate tourDate);

    List<Booking> findByBookingDate(LocalDate fromDate);

    List<Booking> findByUserId(Long userId);

    List<Booking> findByTourId(Long tourId);

}
