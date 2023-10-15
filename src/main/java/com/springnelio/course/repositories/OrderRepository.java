package com.springnelio.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springnelio.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
