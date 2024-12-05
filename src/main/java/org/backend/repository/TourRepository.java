package org.backend.repository;


import org.backend.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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



    @Query("SELECT t FROM Tour t WHERE " +
            "(:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:state IS NULL OR t.state = :state) AND " +
            "(:price IS NULL OR t.price <= :price) AND " +
            "(:duration IS NULL OR t.duration <= :duration) AND " +
            "(:startDate IS NULL OR t.startDate >= :startDate) AND " +
            "(:endDate IS NULL OR t.endDate <= :endDate) AND " +
            "(:country IS NULL OR LOWER(t.country) = LOWER(:country)) AND " +
            "(:city IS NULL OR LOWER(t.city) = LOWER(:city))")
    List<Tour> searchTours(@Param("title") String title,
                           @Param("state") Tour.State state,
                           @Param("price") Long price,
                           @Param("duration") Long duration,
                           @Param("startDate") LocalDate startDate,
                           @Param("endDate") LocalDate endDate,
                           @Param("country") String country,
                           @Param("city") String city);
}