package com.springnelio.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springnelio.course.entities.Order;
import com.springnelio.course.repositories.OrderRepository;

@Service
public class OrderServices {

	@Autowired
	private OrderRepository repository;

	public List<Order> findAll() {
		return repository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> user = repository.findById(id);
		return user.get();
	}
}
