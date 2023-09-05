package com.springboot.voting.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.springboot.voting.dto.UserAuthDetails;
import com.springboot.voting.model.User;
import com.springboot.voting.repository.UserRepository;

public class UserAuthService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Employee not found for username " + username));

		UserDetails userDetails = new UserAuthDetails(user);

		return userDetails;
	}
}
