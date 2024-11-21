package org.backend.repository;

import org.backend.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface StateRepository extends JpaRepository<State, Long>{
    @Override
    Optional<State> findById(Long id);
}
