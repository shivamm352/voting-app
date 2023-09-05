package com.springboot.voting.service;

import java.util.List;

import com.springboot.voting.model.User;

public interface UserService {
	List<User> getAllUsers();

	User getUserById(int userId);

	User createUser(User user);

	User updateUser(int userId, User user);

	void deleteUser(int userId);

	boolean isCUrrentUserVoted();

	String getLoggedInUsername();
}
