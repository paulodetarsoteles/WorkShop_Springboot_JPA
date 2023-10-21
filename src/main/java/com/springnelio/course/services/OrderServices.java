package com.springnelio.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springnelio.course.entities.Order;
import com.springnelio.course.repositories.OrderRepository;
import com.springnelio.course.services.exceptions.ResourceNotFoundException;

@Service
public class OrderServices {

	@Autowired
	private OrderRepository repository;

	public List<Order> findAll() {
		return repository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> order = repository.findById(id);
		return order.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Order insert(Order obj) {
		return repository.save(obj);
	}
	
	public Order update(Long id, Order obj) {
		Order entity = repository.getReferenceById(id);
		
		entity.setOrderStatus(obj.getOrderStatus());
		entity.setPayment(obj.getPayment());
		 
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
