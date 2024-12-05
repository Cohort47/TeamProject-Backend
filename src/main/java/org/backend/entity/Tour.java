package org.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tours")
public class Tour {
    public void setState(State state) {

    }

    public enum State {
        AVAILABLE,
        CLOSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 70)
    private String title;

    @Column(nullable = false)
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
    private Tour.State state;

    @ElementCollection
    @CollectionTable(name = "tour_photos", joinColumns = @JoinColumn(name = "tour_id"))
    @Column(name = "photo_link")
    private List<String> photoLinks = new ArrayList<>();

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();

}
