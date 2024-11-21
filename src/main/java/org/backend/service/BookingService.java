package org.backend.service;


import lombok.extern.slf4j.Slf4j;
import org.backend.dto.BookingDto.BookingDto;
import org.backend.entity.Booking;
import org.backend.repository.BookingRepository;
import org.backend.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Booking createBooking(Long userId, Long tourId) {
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setTourId(tourId);
        booking.setState(Booking.State.AVAILABLE);
        return bookingRepository.save(booking);
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
        bookingRepository.deleteById(bookingId);
    }

    @Transactional
    public Booking getBooking (Long bookingId){
           return bookingRepository.findById(bookingId)
                   .orElseThrow(() -> new NotFoundException("Booking with ID "
                           + bookingId + " not found"));
        }

    public List<Booking> findAllFull() {
        return bookingRepository.findAll();
    }

    public List<BookingDto> findAll() {
        return BookingDto.from(bookingRepository.findAll());
    }

    public BookingDto getBookingByState(String bookingState) {
        Booking booking = bookingRepository.findBookingByState(bookingState)
                .orElseThrow(() -> new NotFoundException("Booking with state "
                        + bookingState + " not found"));
        return BookingDto.from(booking);
    }

    public BookingDto getBookingById(long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking with ID "
                        + bookingId + " not found"));
        return BookingDto.from(booking);
    }
}
