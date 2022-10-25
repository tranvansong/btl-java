package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public Category findByNameContainingIgnoreCase(String name);
	
	@Query("SELECT c FROM Category c WHERE c.name LIKE %?1%") // tim kiem theo tu co trong "name"
	public Page<Category> findByName(String name, Pageable pageable);
	
}
