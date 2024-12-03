package org.backend.repository;

import org.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT b FROM Booking b " +
            "WHERE b.user.id = :userId " +
            "AND (:state IS NULL OR b.state = :state) " +
            "AND (:bookingDate IS NULL OR b.bookingDate = :bookingDate) " +
            "AND (:tourDate IS NULL OR b.tourDate = :tourDate) " +
            "AND (:tourId IS NULL OR b.tour.id = :tourId) " +
            "AND (:amountOfPeople IS NULL OR b.amountOfPeople = :amountOfPeople)")
    List<Booking> getBookings(@Param("userId") Long userId,
                                 @Param("state") Booking.State state,
                                 @Param("bookingDate") LocalDate bookingDate,
                                 @Param("tourDate") LocalDate tourDate,
                                 @Param("tourId") Long tourId,
                                 @Param("amountOfPeople") Integer amountOfPeople);

}
