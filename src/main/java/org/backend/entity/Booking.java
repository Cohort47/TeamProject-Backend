package org.backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private Long tour_id;

    public enum State {
        AVAILABLE,
        BOOKED,
        PAID,
        CANCELLED
    }
}
