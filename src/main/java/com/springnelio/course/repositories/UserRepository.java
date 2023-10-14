package com.springnelio.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springnelio.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
