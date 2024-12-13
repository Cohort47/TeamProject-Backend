package org.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class User {

    public enum Role {
        ADMIN,
        USER
    }

    public enum State {
        NOT_CONFIRMED,
        CONFIRMED,
        DELETED,
        BANNED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashPassword;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "users_role",
//    joinColumns = @JoinColumn(name = "user_id", referencedColumnName ="id"),
//    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Collection<Role> roles;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(nullable = false)
    private boolean isDeleted = false;

    private String photoLink;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Это игнорирует сериалиацию связанного списка bookings
    private List<Booking> bookings = new ArrayList<>();

}
