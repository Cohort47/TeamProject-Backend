package org.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.backend.dto.tourDto.TourRequestDto;
import org.backend.dto.tourDto.TourResponseDto;
import org.backend.entity.Tour;
import org.backend.repository.TourRepository;
import org.backend.service.exception.AlreadyExistException;
import org.backend.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TourService {
    private final TourRepository tourRepository;

    public TourResponseDto addTour(TourRequestDto newTour) {

        Tour tour = Tour.builder()
                .title(newTour.getTitle())
                .description(newTour.getDescription())
                .price(newTour.getPrice())
                .duration(newTour.getDuration())
                .startDate(newTour.getStartDate())
                .endDate(newTour.getEndDate())
                .state(Tour.State.valueOf(newTour.getState()))
                .photoLinks(newTour.getPhotoLinks())
                .country(newTour.getCountry())
                .city(newTour.getCity())
                .build();

        tourRepository.save(tour);

        return TourResponseDto.from(tour);

    }


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
    public TourResponseDto updateTourState(Long tourId, String newState) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour with ID " + tourId + " not found"));

        // Преобразуем строку в перечисление State
        try {
            Tour.State stateEnum = Tour.State.valueOf(newState.toUpperCase());
            tour.setState(stateEnum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid state: " + newState + ". Allowed values are: " + Arrays.toString(Tour.State.values()));
        }

        return TourResponseDto.from(tourRepository.save(tour));
    }

    public TourResponseDto updateTour(Long id, TourRequestDto updateRequest) {
        // Проверяем, существует ли тур
        Tour existingTour = tourRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tour with ID " + id + " not found"));

        // Объединяем ссылки на фото
        List<String> updatedPhotoLinks = new ArrayList<>(existingTour.getPhotoLinks());
        if (updateRequest.getPhotoLinks() != null && !updateRequest.getPhotoLinks().isEmpty()) {
            updatedPhotoLinks = new ArrayList<>(updateRequest.getPhotoLinks());
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

    // Метод для удаления тура по ID
    public void deleteTour(Long tourId) {
        // Проверяем, существует ли тур с таким ID
        if (tourRepository.existsById(tourId)) {
            // Если существует, удаляем его
            tourRepository.deleteById(tourId);
        } else {
            // Если тура с таким ID не существует, выбрасываем исключение
            throw new IllegalArgumentException("Tour with id " + tourId + " not found");
        }
    }
}


