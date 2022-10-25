package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public ResponseEntity<List<ProductDTO>> getAllPages(@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer minPrice,
			@RequestParam(required = false) Integer maxPrice,
			@RequestParam(required = false, name = "category") String categoryName) {
		
		List<ProductDTO> pages = productService.findAll();
		if(pages == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
		if(name == null && minPrice == null && maxPrice == null && categoryName == null) {
			pages = productService.findAll();
		}else if(name != null) {
			pages = productService.findByName(name);
		}else if(categoryName != null) {
			pages = productService.findProductsByCategoryName(categoryName);
		}else {
			pages = productService.filterByPrice(minPrice, maxPrice);
		}
		for (ProductDTO productDTO : pages) {
			System.out.println(productDTO);
		}
		return new ResponseEntity<>(pages,HttpStatus.OK);
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<Object> createNewProduct(@RequestBody ProductDTO productDTO) {
		Category category = categoryService.findByName(productDTO.getCategoryName());
		if(category == null) {
			return new ResponseEntity<>("Category is invalid",HttpStatus.CONFLICT);
		}
		return null;
		
	}
	
}
