package org.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
@Entity
public class Booking {

    public enum State {
        BOOKED,
        PAID,
        CANCELLED
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;


    // Дата создания бронирования
    @Column(nullable = false)
    private LocalDate bookingDate;


    // Дата начала тура, выбранная пользователем
    @Column(nullable = false)
    private LocalDate tourDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @Column(nullable = false)
    private Integer amountOfPeople;

}
