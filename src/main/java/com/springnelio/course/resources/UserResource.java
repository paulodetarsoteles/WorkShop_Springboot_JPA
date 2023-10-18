package com.springnelio.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping(value = "insert")
	public ResponseEntity<User> insert(@RequestBody User obj){
		
		User result = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("users/findById/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(result);
	}
	
	@PutMapping(value = "update/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		User result = service.update(id, obj);
		
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping(value = "/delete/{id}")
 	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
