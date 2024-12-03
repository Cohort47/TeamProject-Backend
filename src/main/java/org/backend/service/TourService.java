package org.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.backend.dto.tourDto.TourRequestDto;
import org.backend.dto.tourDto.TourResponseDto;
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


    public List<TourResponseDto> findAll() {
        return TourResponseDto.from(tourRepository.findAll());
    }


    public TourResponseDto getTourById(Long tourId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour with ID " + tourId + " not found"));
        return TourResponseDto.from(tour);
    }

    public List<TourResponseDto> searchTours(String title, String state, Long price, Long duration, LocalDate startDate, LocalDate endDate, String country, String city) {
        Tour.State stateEnum = null;
        if (state != null) {
            try {
                stateEnum = Tour.State.valueOf(state.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid state value: " + state);
            }
        }
        // Пример базовой логики фильтрации
        return tourRepository.searchTours(title, stateEnum, price, duration, startDate, endDate, country, city)
                .stream()
                .map(TourResponseDto::from)
                .toList();
    }

    @Transactional
    public Tour updateTourState(Long tourId, Tour.State newState) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour with ID " + tourId + " not found"));
        tour.setState(newState);
        return tourRepository.save(tour);
    }

    public TourResponseDto updateTour(Long id, TourRequestDto updateRequest) {
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

        return TourResponseDto.from(updatedTour);
    }
}


