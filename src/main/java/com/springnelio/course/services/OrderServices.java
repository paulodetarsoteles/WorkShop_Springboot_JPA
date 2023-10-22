package com.springnelio.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.springnelio.course.entities.Order;
import com.springnelio.course.repositories.OrderRepository;
import com.springnelio.course.services.exceptions.DbIntegrityException;
import com.springnelio.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		try {
			Order entity = repository.getReferenceById(id);
		
			entity.setOrderStatus(obj.getOrderStatus());
			entity.setPayment(obj.getPayment());
		 
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
