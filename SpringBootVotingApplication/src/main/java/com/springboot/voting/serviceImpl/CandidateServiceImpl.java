package com.springboot.voting.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.voting.model.Candidate;
import com.springboot.voting.repository.CandidateRepository;
import com.springboot.voting.repository.UserRepository;
import com.springboot.voting.service.CandidateService;
import com.springboot.voting.service.UserService;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Override
	public List<Candidate> getAllCandidates() {
		return candidateRepository.findAll();
	}

	@Override
	public Candidate getCandidateById(int id) {
		Optional<Candidate> candidateOptional = candidateRepository.findById(id);
		return candidateOptional.orElse(null);
	}

	@Override
	public void createCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
	}

	@Override
	public void deleteCandidate(int id) {
		candidateRepository.deleteById(id);
	}

	@Override
	public void vote(int candidateId) {
		Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
		if (candidate != null) {
			candidate.setVotes(candidate.getVotes() + 1);
			candidateRepository.save(candidate);
			userRepository.markUserVoted(userService.getLoggedInUsername());
		}

	}
}
