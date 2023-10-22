package com.springnelio.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.springnelio.course.entities.User;
import com.springnelio.course.repositories.UserRepository;
import com.springnelio.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserServices {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		return repository.save(obj);
	}

	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id);
		
		if (entity == null)
			throw new ResourceNotFoundException(id);

		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());

		return repository.save(entity);
	}

	public void delete(Long id) {
		try {
			if (!repository.existsById(id))
				throw new ResourceNotFoundException(id);
		
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			//TODO thrown new person exception
		}
	}
}
