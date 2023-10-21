package com.springnelio.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springnelio.course.entities.OrderItem;
import com.springnelio.course.repositories.OrderItemRepository;
import com.springnelio.course.services.exceptions.ResourceNotFoundException;

@Service
public class OrderItemServices {
	
	@Autowired
	private OrderItemRepository repository;
	
	public List<OrderItem> findAll(){
		return repository.findAll();
	}
	
	public OrderItem findById(Long id) {
		Optional<OrderItem> orderItems = repository.findById(id);
		return orderItems.orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
