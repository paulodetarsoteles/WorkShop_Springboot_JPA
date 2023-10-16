package com.springnelio.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springnelio.course.entities.OrderItem;
import com.springnelio.course.services.OrderItemServices;

@RestController
@RequestMapping(value = "/orderItems")
public class OrderItemResource {

	@Autowired
	private OrderItemServices service;

	@GetMapping(value = "findAll")
	public ResponseEntity<List<OrderItem>> findAll() {
		List<OrderItem> orderItems = service.findAll();

		return ResponseEntity.ok().body(orderItems);
	}

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<OrderItem> findById(@PathVariable Long id) {
		if (id <= 0)
			return ResponseEntity.badRequest().build();

		OrderItem orderItem = service.findById(id);

		if (orderItem == null)
			return ResponseEntity.noContent().build();

		return ResponseEntity.ok().body(orderItem);
	}
}
