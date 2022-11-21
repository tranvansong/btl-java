package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.name LIKE %?1%" 
			+ " OR u.email LIKE %?1%"
			+ " OR u.address LIKE %?1%"
			+ " OR u.phone LIKE %?1%")
	public List<User> findByKeyword(String keyword);
	
	public User findByEmail(String email);
}
