package com.springnelio.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.springnelio.course.entities.User;
import com.springnelio.course.repositories.UserRepository;
import com.springnelio.course.services.exceptions.DbIntegrityException;
import com.springnelio.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		try {
			User entity = repository.getReferenceById(id);

			entity.setName(obj.getName());
			entity.setEmail(obj.getEmail());
			entity.setPhone(obj.getPhone());

			return repository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void delete(Long id) {
		try {
			if (!repository.existsById(id))
				throw new ResourceNotFoundException(id);
		
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DbIntegrityException(e.getMessage()); 
		}
	}
}
