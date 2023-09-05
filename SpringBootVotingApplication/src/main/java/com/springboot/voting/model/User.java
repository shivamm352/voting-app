package com.springboot.voting.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;

	@Pattern(regexp = "^[A-Za-z ]+$", message = "Name Should Contain only Character")
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank(message = "Username is required")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "Username must be alphanumeric")
	@Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
	private String username;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Size(min = 6, message = "Password Must Contain More Then Five Characters")
	@Column(name = "password", nullable = false)
	private String password;

	@Pattern(regexp = "^\\+91\\d{10}$", message = "Invalid Contact")
	@Column(name = "mobile", nullable = false)
	private String phone;

	private boolean voted = false;

	@Column(name = "Roles")
	private List<String> roles = new ArrayList<>();

	public User() {
	}

	public User(int userId,
			@Pattern(regexp = "^[A-Za-z ]+$", message = "Name Should Contain only Character") String name,
			@NotBlank(message = "Username is required") @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric") @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters") String username,
			String email, @Size(min = 6, message = "Password Must Contain More Then Five Characters") String password,
			@Pattern(regexp = "^\\+91\\d{10}$", message = "Invalid Contact") String phone, boolean voted,
			List<String> roles) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.voted = voted;
		this.roles = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public boolean isVoted() {
		return voted;
	}

	public void setVoted(boolean haveVoted) {
		this.voted = haveVoted;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", voted=" + voted + ", roles=" + roles + "]";
	}

}
