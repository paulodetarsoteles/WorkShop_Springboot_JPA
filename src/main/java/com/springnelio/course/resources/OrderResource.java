package com.springnelio.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springnelio.course.entities.Order;
import com.springnelio.course.services.OrderServices;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderServices service;

	@GetMapping(value = "findAll")
	public ResponseEntity<List<Order>> findAll() {
		List<Order> orders = service.findAll();

		return ResponseEntity.ok().body(orders);
	}

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		if (id <= 0)
			return ResponseEntity.badRequest().build();

		Order order = service.findById(id);

		if (order == null)
			return ResponseEntity.noContent().build();

		return ResponseEntity.ok().body(order);
	}
}
