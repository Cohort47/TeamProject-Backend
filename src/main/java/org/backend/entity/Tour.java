package org.backend.entity;

import jakarta.persistence.*;
import liquibase.datatype.core.DecimalType;

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

    @Column(length = 470)
    private String description;


    @Column(length = 10, nullable = false)
    private Long price;

    @Column(length = 10)
    private Long duration;

    @Column (nullable = false)
    private DecimalType startDate;

    @Column (nullable = false)
    private DecimalType endDate;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private User.State state;
}
