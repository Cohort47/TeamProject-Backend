package org.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.backend.dto.tourDto.TourDto;
import org.backend.entity.Tour;
import org.backend.repository.TourRepository;
import org.backend.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TourService {
    private final TourRepository tourRepository;

    public List<TourDto> findAll(){
        return TourDto.from(tourRepository.findAll());
    }

    public TourDto getTourById(Long tourId){
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour with ID "
                        + tourId + " not found"));
        return TourDto.from(tour);
    }


    @Transactional
    public TourDto makeTourClosed(String title){
        Tour tour = tourRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Tour with title "
                        + title + " not found"));

        tour.setState(Tour.State.CLOSED);
        tourRepository.save(tour);

        return TourDto.from(tour);
    }


    public List<Tour> findAllFull(){
        return tourRepository.findAll();
    }

    public TourDto getTourByTitle(String title) {
        Tour tour = tourRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Tour with title "
                        + title + " not found"));
        return TourDto.from(tour);
    }

    public TourDto getTourByState(String tourState) {
        Tour tour = tourRepository.findTourByState(tourState)
                .orElseThrow(() -> new NotFoundException("Tour with state "
                        + tourState + " not found"));
        return TourDto.from(tour);
    }

    public TourDto getTourByPrice(long price) {
        Tour tour = tourRepository.findByPrice(price)
                .orElseThrow(() -> new NotFoundException("Tour with Price "
                        + price + " not found"));
        return TourDto.from(tour);
    }

    public TourDto getTourByDuration(long duration) {
        Tour tour = tourRepository.findByDuration(duration)
                .orElseThrow(() -> new NotFoundException("Tour with Duration "
                        + duration + " not found"));
        return TourDto.from(tour);
    }

    public TourDto getTourByStartDate(LocalDate startDate) {
        Tour tour = tourRepository.findByStartDate(startDate)
                .orElseThrow(() -> new NotFoundException("Tour with StartDate "
                        + startDate + " not found"));
        return TourDto.from(tour);
    }

    public TourDto getTourByEndDate(LocalDate endDate) {
        Tour tour = tourRepository.findByEndDate(endDate)
                .orElseThrow(() -> new NotFoundException("Tour with EndDate "
                        + endDate + " not found"));
        return TourDto.from(tour);
    }

    public Tour updateTourState(Long tourId, Tour.State newState) {
        Tour tour = tourRepository
                .findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour with ID "
                        + tourId + " not found"));
        tour.setState(newState);
        return tourRepository.save(tour);
    }

    public TourDto getTourByCountry(String country) {
        Tour tour = tourRepository.findTourByCountry(country)
                .orElseThrow(() -> new NotFoundException("Tour with country "
                        + country + " not found"));
        return TourDto.from(tour);
    }

    public TourDto getTourByCity(String city) {
        Tour tour = tourRepository.findTourByCity(city)
                .orElseThrow(() -> new NotFoundException("Tour with city "
                        + city + " not found"));
        return TourDto.from(tour);
    }
}


