package org.backend.entity;

import jakarta.persistence.*;
import liquibase.datatype.core.DecimalType;

import java.time.LocalDate;

public class Tour {
    public enum State {
        AVAILABLE,
        CLOSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 70)
    private String title;

    @Column(length = 255)
    private String description;


    @Column(nullable = false)
    private Long price;

    @Column(length = 10)
    private Long duration;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private
    State state;
}
