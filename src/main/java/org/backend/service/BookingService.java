package org.backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.backend.dto.BookingDto.BookingDto;
import org.backend.entity.Booking;
import org.backend.entity.Tour;
import org.backend.entity.User;
import org.backend.repository.BookingRepository;
import org.backend.repository.TourRepository;
import org.backend.repository.UserRepository;
import org.backend.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;


    @Transactional
    public Booking createBooking(Long userId, Long tourId, LocalDate tourDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with ID " + userId + " not found"));
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
        booking.setState(Booking.State.AVAILABLE);


        return bookingRepository.save(booking);
    }

    @Transactional
    public BookingDto getBookingById(Long bookingId) {
        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking with ID "
                        + bookingId + " not found"));
        return BookingDto.from(booking); }


    public List<BookingDto> getBookingsByTourDate(LocalDate tourDate) {
        List<Booking> bookings = bookingRepository.findByTourDate(tourDate);
        if (bookings.isEmpty()) {
            throw new NotFoundException("No bookings found for tour date " + tourDate);
        }
        return bookings.stream()
                .map(BookingDto::from)
                .toList();
    }

    public  List<BookingDto>  getBookingsByBookingDate(LocalDate bookingDate) {
        List<Booking> bookings = bookingRepository.findByBookingDate(bookingDate);
        if (bookings.isEmpty()) {
            throw new NotFoundException("No bookings found for tour date " + bookingDate);
        }
        return bookings.stream()
                .map(BookingDto::from)
                .toList();
    }

    public List<BookingDto> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(BookingDto::from)
                .collect(Collectors.toList());
    }

    public List<BookingDto> getBookingsByTour(Long tourId) {
        return bookingRepository.findByTourId(tourId)
                .stream()
                .map(BookingDto::from)
                .collect(Collectors.toList());
    }

    public List<BookingDto> findBookingByState(Booking.State state) {
        // Получаем все заказы с указанным состоянием из репозитория
        List<Booking> bookings = bookingRepository.findBookingByState(state);

        // Преобразуем найденные заказы в BookingDto и возвращаем их
        return bookings.stream()
                .map(BookingDto::from)
                .toList();
    }


    public List<BookingDto> findAll() {
        List<Booking> bookings = bookingRepository.findAll();
        if (bookings.isEmpty()) {
            throw new NotFoundException("No bookings found");
        }
        return bookings.stream()
                .map(BookingDto::from)
                .toList();
    }

    @Transactional
    public Booking updateBookingState (Long bookingId, Booking.State newState){
        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking with ID "
                        + bookingId + " not found"));
        booking.setState(newState);
        return bookingRepository.save(booking);
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