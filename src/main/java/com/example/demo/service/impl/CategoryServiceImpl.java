package com.example.demo.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public Page<CategoryDTO> findAll(Pageable pageable) {
		Page<Category> categoryPage = categoryRepository.findAll(pageable);
		Page<CategoryDTO> dtoPage = categoryPage.map(category -> new CategoryDTO(category));
		return dtoPage;
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
	public CategoryDTO getById(Integer id) {
		return new CategoryDTO(categoryRepository.findById(id).orElse(null));
	}


	@Override
	public Page<CategoryDTO> findByName(String name, Pageable page) {
		Page<Category> categoryPage = categoryRepository.findByName(name, page);
		Page<CategoryDTO> dtoPage = categoryPage.map(category -> new CategoryDTO(category));
		return dtoPage;
	}


	@Override
	public CategoryDTO findByName(String name) {
		return new CategoryDTO(categoryRepository.findByNameContainingIgnoreCase(name));
	}


	@Override
	public List<CategoryDTO> findAll() {
		List<Category> listCategories = categoryRepository.findAll();
		List<CategoryDTO> dtos = new ArrayList<>();
		for (Category category : listCategories) {
			CategoryDTO categoryDTO = new CategoryDTO(category);
			dtos.add(categoryDTO);
		}
		return dtos;
	}


	@Override
	public Category convertFromCategoryDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getCreated_at(), categoryDTO.getUpdated_at());
	}


	@Override
	public CategoryDTO findExactlyName(String name) {
		Category category = categoryRepository.findByName(name);
		return new CategoryDTO(category);
	}


	@Override
	public Category update(CategoryDTO categoryDTO) {
		Category category = categoryRepository.findById(categoryDTO.getId()).get();
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setCreated_at(category.getCreated_at());
		category.setUpdated_at(categoryDTO.getUpdated_at());
		return category;
	}

	@Override
	public boolean isDuplicatedName(String name) {
		List<CategoryDTO> list = this.findAll();
		CategoryDTO foundCategory = list.stream().filter(c -> c.getName().toLowerCase().equals(name.toLowerCase())).findFirst().orElse(null);
		if(foundCategory != null) return true;
		return false;
	}

}
