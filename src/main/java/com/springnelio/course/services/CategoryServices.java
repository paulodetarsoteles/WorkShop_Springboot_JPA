package com.springnelio.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springnelio.course.entities.Category;
import com.springnelio.course.repositories.CategoryRepository;
import com.springnelio.course.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryServices {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){		
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> category = repository.findById(id);
		return category.orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
