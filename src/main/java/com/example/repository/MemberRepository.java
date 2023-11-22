package com.example.repository;

import com.example.model.Member;
import com.example.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findByFirstName(String firstName, Pageable pageable);
    Page<Member> findByLastName(String lastName, Pageable pageable);
    Page<Member> findByAddress(String address, Pageable pageable);
    Page<Member> findByTeam_Id(Long id, Pageable pageable);
    Page<Member> findAll(Pageable pageable);
}
