package com.springnelio.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springnelio.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
