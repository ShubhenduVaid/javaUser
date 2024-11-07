package com.billion.user.controller;

import com.billion.user.entity.User;
import com.billion.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		List<User> userList = userService.getAllUsers();
		return ResponseEntity.ok(userList);
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<User> getUser(@PathVariable UUID id) {
//		Optional<User> user = userService.getUserById(id);
//		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//	}

	@GetMapping("/{country}/{id}")
	public ResponseEntity<User> getUserByCountryAndID(@PathVariable String country, @PathVariable UUID id){
		return userService.getUserByCountryAndId(country, id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{country}")
	public ResponseEntity<List<User>> getUsersByCountry(@PathVariable String country){
		return userService.getUserByCountry(country)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<User> createBook(@RequestBody User userRequest) {

		User user = new User();
		user.setId(UUID.randomUUID());
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setAddress(userRequest.getAddress());
		user.setDob(LocalDate.now());
		user.setPhone(userRequest.getPhone());
		user.setCountry(userRequest.getCountry());
		user.setDate_created(LocalDateTime.now());

		user = userService.saveUser(user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
}
