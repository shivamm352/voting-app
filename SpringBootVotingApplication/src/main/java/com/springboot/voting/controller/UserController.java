package com.springboot.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.springboot.voting.model.Candidate;
import com.springboot.voting.model.User;
import com.springboot.voting.service.CandidateService;
import com.springboot.voting.service.UserService;

@Controller
@RequestMapping({ "/users" })
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping({ "/", "/login" })
	public String showLoginForm() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user, Model model) {
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);

		if (user.getRoles().isEmpty()) {
			user.setRoles(List.of("ROLE_USER"));
		}

		userService.createUser(user);

		String successMessage = "Registered successfully!";
		model.addAttribute("successMessage", successMessage);

		return "redirect:/users/login";
	}

	@GetMapping("/voting")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public String showVotingPage(Model model) {
		List<Candidate> candidates = candidateService.getAllCandidates();
		model.addAttribute("candidates", candidates);
		model.addAttribute("candidateResponse", new Candidate());
		return "voting";
	}

	@PostMapping("/vote")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public String submitVote(@ModelAttribute("candidateResponse") Candidate candidate, Model model) {
		String successMessage = "Vote submitted successfully!";

		if (!userService.isCUrrentUserVoted()) {
			candidateService.vote(candidate.getId());
		} else {
			successMessage = "Sorry you have already voted earlier";
			System.out.println(successMessage);
		}
		model.addAttribute("successMessage", successMessage);

		return "successfulVote";
	}

	@GetMapping("/successfulVote")
	public String showVoteSuccessPage(Model model) {
		return "successfulVote";
	}

}
