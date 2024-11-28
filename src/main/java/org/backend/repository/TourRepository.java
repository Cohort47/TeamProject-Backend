package org.backend.repository;


import org.backend.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;




public interface TourRepository extends JpaRepository<Tour, Long> {

    // Find a tour by its ID
    Optional<Tour> findById(Long id);

    // Check existence by ID
    boolean existsById(Long id);

    // Find tours by title
    List<Tour> findByTitle(String title);


    // Find tours by state
    List<Tour> findByState(Tour.State state);


    // Find tours by price
    List<Tour> findByPrice(long price);


    // Find tours by duration
    List<Tour> findByDuration(long duration);


    // Find tours by start date
    List<Tour> findByStartDate(LocalDate startDate);

    // Find tours by end date
    List<Tour> findByEndDate(LocalDate endDate);

    // Find tours by country
    List<Tour> findByCountry(String country);

    // Find tours by city
    List<Tour> findByCity(String city);

}