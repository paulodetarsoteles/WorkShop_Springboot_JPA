package com.springnelio.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springnelio.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
