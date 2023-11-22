package com.example.repository;

import com.example.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Page<Team> findByName(String name, Pageable pageable);
    Page<Team> findByMascot(String mascot, Pageable pageable);
    Page<Team> findByLocation(String location, Pageable pageable);
    Page<Team> findAll(Pageable pageable);
}
