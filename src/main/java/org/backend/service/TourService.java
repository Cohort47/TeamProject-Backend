package org.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.backend.dto.tourDto.TourDto;
import org.backend.dto.tourDto.TourUpdateRequest;
import org.backend.entity.Tour;
import org.backend.repository.TourRepository;
import org.backend.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TourService {
    private final TourRepository tourRepository;


    public List<TourDto> findAll() {
        return TourDto.from(tourRepository.findAll());
    }

    public List<Tour> findAllFull() {
        return tourRepository.findAll();
    }

    public TourDto getTourById(Long tourId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour with ID " + tourId + " not found"));
        return TourDto.from(tour);
    }

    public List<TourDto> getToursByTitle(String title) {
        List<Tour> tours = tourRepository.findByTitle(title);
        if (tours.isEmpty()) {
            throw new NotFoundException("No tours found with title " + title);
        }
        return TourDto.from(tours);
    }

    public List<TourDto> getToursByState(String tourState) {
        List<Tour> tours = tourRepository.findByState(Tour.State.valueOf(tourState));
        if (tours.isEmpty()) {
            throw new NotFoundException("No tours found with state " + tourState);
        }
        return TourDto.from(tours);
    }

    public List<TourDto> getToursByPrice(long price) {
        List<Tour> tours = tourRepository.findByPrice(price);
        if (tours.isEmpty()) {
            throw new NotFoundException("No tours found with price " + price);
        }
        return TourDto.from(tours);
    }

    public List<TourDto> getToursByDuration(long duration) {
        List<Tour> tours = tourRepository.findByDuration(duration);
        if (tours.isEmpty()) {
            throw new NotFoundException("No tours found with duration " + duration);
        }
        return TourDto.from(tours);
    }

    public List<TourDto> getToursByStartDate(LocalDate startDate) {
        List<Tour> tours = tourRepository.findByStartDate(startDate);
        if (tours.isEmpty()) {
            throw new NotFoundException("No tours found with start date " + startDate);
        }
        return TourDto.from(tours);
    }

    public List<TourDto> getToursByEndDate(LocalDate endDate) {
        List<Tour> tours = tourRepository.findByEndDate(endDate);
        if (tours.isEmpty()) {
            throw new NotFoundException("No tours found with end date " + endDate);
        }
        return TourDto.from(tours);
    }

    public List<TourDto> getToursByCountry(String country) {
        List<Tour> tours = tourRepository.findByCountry(country);
        if (tours.isEmpty()) {
            throw new NotFoundException("No tours found in country " + country);
        }
        return TourDto.from(tours);
    }

    public List<TourDto> getToursByCity(String city) {
        List<Tour> tours = tourRepository.findByCity(city);
        if (tours.isEmpty()) {
            throw new NotFoundException("No tours found in city " + city);
        }
        return TourDto.from(tours);
    }

    @Transactional
    public Tour updateTourState(Long tourId, Tour.State newState) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour with ID " + tourId + " not found"));
        tour.setState(newState);
        return tourRepository.save(tour);
    }

    public TourDto updateTour(Long id, TourUpdateRequest updateRequest) {
        // Проверяем, существует ли тур
        Tour existingTour = tourRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tour with ID " + id + " not found"));

        // Объединяем ссылки на фото
        List<String> updatedPhotoLinks = new ArrayList<>(existingTour.getPhotoLinks());
        if (updateRequest.getPhotoLinks() != null) {
            updatedPhotoLinks.addAll(updateRequest.getPhotoLinks());
        }

        // Перезаписываем поля
        existingTour.setTitle(updateRequest.getTitle() != null ? updateRequest.getTitle() : existingTour.getTitle());
        existingTour.setDescription(updateRequest.getDescription() != null ? updateRequest.getDescription() : existingTour.getDescription());
        existingTour.setPrice(updateRequest.getPrice() != null ? updateRequest.getPrice() : existingTour.getPrice());
        existingTour.setDuration(updateRequest.getDuration() != null ? updateRequest.getDuration() : existingTour.getDuration());
        existingTour.setStartDate(updateRequest.getStartDate() != null ? updateRequest.getStartDate() : existingTour.getStartDate());
        existingTour.setEndDate(updateRequest.getEndDate() != null ? updateRequest.getEndDate() : existingTour.getEndDate());
        existingTour.setCountry(updateRequest.getCountry() != null ? updateRequest.getCountry() : existingTour.getCountry());
        existingTour.setCity(updateRequest.getCity() != null ? updateRequest.getCity() : existingTour.getCity());
        existingTour.setState(updateRequest.getState() != null
                ? EnumUtils.getEnumIgnoreCase(Tour.State.class, updateRequest.getState())
                : existingTour.getState());
        existingTour.setPhotoLinks(updatedPhotoLinks);


        Tour updatedTour = tourRepository.save(existingTour);

        return TourDto.from(updatedTour);
    }
}


