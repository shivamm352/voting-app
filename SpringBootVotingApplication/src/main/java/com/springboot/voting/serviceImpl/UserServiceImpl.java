package com.springboot.voting.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springboot.voting.model.User;
import com.springboot.voting.repository.UserRepository;
import com.springboot.voting.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(int userId) {
		return userRepository.findById(userId).orElseThrow();
	}

	@Override
	public User updateUser(int userId, User user) {
		User existingUser = userRepository.findById(userId).orElseThrow();
		existingUser.setName(user.getName());
		existingUser.setUsername(user.getUsername());
		existingUser.setPassword(user.getPassword());
		existingUser.setPhone(user.getPhone());
		existingUser.setEmail(user.getEmail());

		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(int userId) {
		User existingUser = userRepository.findById(userId).orElseThrow();
		userRepository.delete(existingUser);
	}

	@Override
	public String getLoggedInUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		return username;

	}

	@Override
	public boolean isCUrrentUserVoted() {
		return userRepository.isVoted(getLoggedInUsername());
	}
}
