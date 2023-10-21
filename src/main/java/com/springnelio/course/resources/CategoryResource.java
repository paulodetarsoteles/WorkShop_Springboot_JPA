package com.springnelio.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springnelio.course.entities.Category;
import com.springnelio.course.services.CategoryServices;
import com.springnelio.course.services.exceptions.ResourceIncorrectParametersException;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryServices service;

	@GetMapping(value = "findAll")
	public ResponseEntity<List<Category>> findAll() {
		List<Category> categories = service.findAll();

		return ResponseEntity.ok().body(categories);
	}

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		if (id <= 0)
			throw new ResourceIncorrectParametersException(id);

		Category category = service.findById(id);

		if (category == null)
			return ResponseEntity.noContent().build();

		return ResponseEntity.ok().body(category);
	}
}
