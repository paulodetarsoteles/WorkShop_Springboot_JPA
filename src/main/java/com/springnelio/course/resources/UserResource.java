package com.springnelio.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springnelio.course.entities.User;
import com.springnelio.course.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserServices service;

	@GetMapping(value = "findAll")
	public ResponseEntity<List<User>> findAll() {
		List<User> users = service.findAll();

		return ResponseEntity.ok().body(users);
	}

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		if (id <= 0)
			return ResponseEntity.badRequest().build();

		User user = service.findById(id);

		if (user == null)
			return ResponseEntity.noContent().build();

		return ResponseEntity.ok().body(user);
	}
}
