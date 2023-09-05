package com.springboot.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.voting.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
