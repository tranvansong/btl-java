package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	// display list category
	@GetMapping("")
	public ResponseEntity<Map<String, Object>> getAllPages(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "1") int page, 
			@RequestParam(defaultValue = "3") int pageSize, 
			@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String dir) {
		
		Pageable pageable = PageRequest.of(page-1, pageSize, Sort.by(Direction.fromString(dir), sortBy));
		
		Page<Category> pageCategories;
		
		if(name == null) {
			pageCategories = categoryService.findAll(pageable);
		}else {
			pageCategories = categoryService.findByName(name, pageable);
		}
		
		List<Category> listCategories = pageCategories.getContent();
		
		if(listCategories == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		Map<String, Object> response = new HashMap<>();
		response.put("categories", listCategories);
		response.put("totalItems", pageCategories.getTotalElements());
		response.put("totalPages", pageCategories.getTotalPages());
		response.put("currentPage", page);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	// create new category and save 
	@PostMapping("")
	public ResponseEntity<Object> createCategory(@RequestBody Category newCategory) {
		categoryService.save(newCategory);
		return new ResponseEntity<>("Da tao thanh cong", HttpStatus.OK);
	}
	
	
//	 get category by id
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable("id") Integer id) {
		return categoryService.getById(id);
	}
	
	
	// update category
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable("id") Integer id, @RequestBody Category newCategory) {
		Category foundCategory = categoryService.getById(id);
		if(foundCategory == null) {
			return new ResponseEntity<>("Khong tim thay category", HttpStatus.NOT_FOUND);
		}else {
			foundCategory.setName(newCategory.getName());
			foundCategory.setProducts(newCategory.getProducts());
			categoryService.save(foundCategory);
		}
		return new ResponseEntity<>("Da update", HttpStatus.OK);
	}
	
	
	// delete category
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable("id") Integer id) {
		Category foundCategory = categoryService.getById(id);
		
		if(foundCategory == null) {
			return new ResponseEntity<>("Khong tim thay category", HttpStatus.NOT_FOUND);
		}else {			
			categoryService.deleteById(id);
		}
		return new ResponseEntity<>("Da xoa", HttpStatus.OK);
	}
}
