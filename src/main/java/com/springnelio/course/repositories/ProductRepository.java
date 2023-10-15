package com.springnelio.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springnelio.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
