package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}


	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}


	@Override
	public void deleteById(Integer id) {
		Category foundCategory = categoryRepository.findById(id).orElse(null);
		categoryRepository.delete(foundCategory);
	}


	@Override
	public Category getById(Integer id) {
		return categoryRepository.findById(id).orElse(null);
	}


	@Override
	public Page<Category> findByName(String name, Pageable page) {
		return categoryRepository.findByName(name, page);
	}


	@Override
	public Category findByName(String name) {
		return categoryRepository.findByNameContainingIgnoreCase(name);
	}


	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}


}
