package com.springboot.voting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.voting.model.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	@Query(value = "select voted from users where username = :username", nativeQuery = true)
	Boolean isVoted(@Param("username") String username);

	@Modifying
	@Transactional
	@Query(value = "update Users set voted = true where username = :username", nativeQuery = true)
	void markUserVoted(@Param("username") String username);

}
