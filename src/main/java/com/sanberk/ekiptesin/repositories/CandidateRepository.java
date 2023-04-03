package com.sanberk.ekiptesin.repositories;

import com.sanberk.ekiptesin.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    public Optional<Candidate> findByEmail(String email);
}
