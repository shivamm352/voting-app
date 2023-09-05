package com.springboot.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.voting.model.Candidate;
import com.springboot.voting.service.CandidateService;

@Controller
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@PostMapping("/candidate/add")
	public String registerUser(@ModelAttribute Candidate candidate) {

		candidateService.createCandidate(candidate);

		return "redirect:/users/voting";
	}

}
