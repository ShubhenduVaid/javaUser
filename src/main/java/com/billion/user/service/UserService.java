package com.billion.user.service;

import com.billion.user.entity.User;
import com.billion.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> getUserById(UUID id) {
		return userRepository.findById(id);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void deleteUser(UUID id) {
		userRepository.deleteById(id);
	}
}
