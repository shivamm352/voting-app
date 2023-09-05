package com.springboot.voting.service;

import java.util.List;

import com.springboot.voting.model.Candidate;

public interface CandidateService {

	public void createCandidate(Candidate candidate);

	public void updateCandidate(Candidate candidate);

	public List<Candidate> getAllCandidates();

	public Candidate getCandidateById(int id);

	public void deleteCandidate(int id);

	public void vote(int candidateId);

}
