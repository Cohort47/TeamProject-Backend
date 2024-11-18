package org.backend.repository;


import org.backend.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;




public interface TourRepository extends JpaRepository<Tour,Long> {

    Optional<Tour> findById(Long id);

    boolean existsById(Long id);



    Optional<Tour>putTourById(Long id);



    Optional<Tour> findByTitle(String title);

    boolean existsByTitle(String title);

    Optional<Tour>putTourByTitle(String title);




    Optional<Tour> findTourByState(String tourState);

    boolean existsByState(Tour.State state);



    Optional<Tour>  findByPrice(long tourPrice);
    boolean existsByPrice(long tourPrice);



    Optional<Tour> findByDuration(long tourDuration);
    boolean existsByDuration(long tourDuration);



    Optional<Tour> findByStartDate(LocalDate tourStartDate);

    Optional<Tour> findByEndDate(LocalDate tourEndDate);
}
