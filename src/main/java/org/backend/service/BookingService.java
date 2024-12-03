package org.backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.backend.dto.BookingDto.BookingResponseDto;
import org.backend.entity.Booking;
import org.backend.entity.Tour;
import org.backend.entity.User;
import org.backend.repository.BookingRepository;
import org.backend.repository.TourRepository;
import org.backend.repository.UserRepository;
import org.backend.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;


    @Transactional
    public BookingResponseDto createBooking(String userEmail, Long tourId, LocalDate tourDate, Integer amountOfPeople) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("User with ID " + userEmail + " not found"));
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour with ID " + tourId + " not found"));

        if (tourDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Tour date cannot be in the past.");
        }
        Booking booking = new Booking();

        booking.setUser(user);
        booking.setTour(tour);
        // Устанавливаем текущую дату как дату создания
        booking.setBookingDate(LocalDate.now());
        // Дата тура от пользователя
        booking.setTourDate(tourDate);
        booking.setState(Booking.State.BOOKED);
        booking.setAmountOfPeople(amountOfPeople);


        return  BookingResponseDto.from(bookingRepository.save(booking));
    }

    @Transactional
    public BookingResponseDto getBookingById(Long bookingId) {
        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking with ID "
                        + bookingId + " not found"));
        return BookingResponseDto.from(booking);
    }

    @Transactional(readOnly = true)
    public List<BookingResponseDto> getBookings(String userEmail, Booking.State state, LocalDate bookingDate, LocalDate tourDate, Long tourId, Integer amountOfPeople) {
        // Получаем пользователя по email
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("User with email " + userEmail + " not found"));

        // Выполняем фильтрацию через репозиторий
        List<Booking> bookings = bookingRepository.getBookings(user.getId(), state, bookingDate, tourDate, tourId, amountOfPeople);

        // Преобразуем список объектов Booking в BookingResponseDto
        return bookings.stream()
                .map(BookingResponseDto::from) // Преобразуем каждый Booking в BookingResponseDto
                .toList();
    }


    public List<BookingResponseDto> findAll(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("User with email " + userEmail + " not found"));
        List<Booking> bookings = bookingRepository.findByUserId(user.getId());
        if (bookings.isEmpty()) {
            throw new NotFoundException("No bookings found");
        }
        return bookings.stream()
                .map(BookingResponseDto::from)
                .toList();
    }

    @Transactional
    public BookingResponseDto updateBookingState (Long bookingId, Booking.State newState){
        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking with ID "
                        + bookingId + " not found"));
        booking.setState(newState);
        return BookingResponseDto.from(bookingRepository.save(booking));
    }

    @Transactional
    public void deleteBooking (Long bookingId){
        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking with ID "
                        + bookingId + " not found"));
        bookingRepository.delete(booking);
    }



}