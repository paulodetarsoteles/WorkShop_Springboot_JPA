package com.springnelio.course.resources;

import java.net.URI;
import java.time.Instant;
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

import com.springnelio.course.entities.Order;
import com.springnelio.course.entities.enums.OrderStatus;
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
	
	@PostMapping(value = "insert")
	public ResponseEntity<Order> insert(@RequestBody Order obj){
		
		obj.setMoment(Instant.now());
		obj.setOrderStatus(OrderStatus.WAITING_PAYMENT);
		
		Order result = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("users/findById/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(result);
	}
	
	@PutMapping(value = "update/{id}")
	public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj){
		Order result = service.update(id, obj);
		
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping(value = "/delete/{id}")
 	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
