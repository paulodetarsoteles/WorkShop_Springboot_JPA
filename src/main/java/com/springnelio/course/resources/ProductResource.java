package com.springnelio.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springnelio.course.entities.Product;
import com.springnelio.course.services.ProductServices;
import com.springnelio.course.services.exceptions.ResourceIncorrectParametersException;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductServices service;

	@GetMapping(value = "findAll")
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = service.findAll();

		return ResponseEntity.ok().body(products);
	}

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		if (id <= 0)
			throw new ResourceIncorrectParametersException(id);

		Product product = service.findById(id);

		if (product == null)
			return ResponseEntity.noContent().build();

		return ResponseEntity.ok().body(product);
	}
}
