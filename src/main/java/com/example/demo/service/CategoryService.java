package com.example.demo.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;

public interface CategoryService {
	public List<CategoryDTO> findAll();
	public Page<CategoryDTO> findAll(Pageable pageable);
	public void save(Category category);
	public Category update(CategoryDTO categoryDTO);
	public void deleteById(Integer id);
	public CategoryDTO getById(Integer id);
	public CategoryDTO findByName(String name);
	public CategoryDTO findExactlyName(String name);
	public Page<CategoryDTO> findByName(String name, Pageable pageable);
	public Category convertFromCategoryDTO(CategoryDTO categoryDTO);
	public boolean isDuplicatedName(String name);
}