package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%" 
			+ " OR p.category.name LIKE %?1%")
	public List<Product> findByKeyword(String name);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
	public List<Product> findByName(String name);
	
	@Query("SELECT p FROM Product p WHERE p.price between ?1 and ?2")
	public List<Product> filterByPrice(int minPrice, int maxPrice);
}
