package com.example.demo.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;

public interface CategoryService {
	public List<Category> findAll();
	public Page<Category> findAll(Pageable pageable);
	public void save(Category category);
	public void deleteById(Integer id);
	public Category getById(Integer id);
	public Category findByName(String name);
	public Page<Category> findByName(String name, Pageable pageable);
}