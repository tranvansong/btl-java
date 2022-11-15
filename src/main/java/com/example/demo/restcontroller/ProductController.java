package com.example.demo.restcontroller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000/")
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
	
	@GetMapping("/{id}")
	public ProductDTO getProductById(@PathVariable("id") Integer id) { 
		return productService.findById(id);
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<Object> createNewProduct(@RequestBody ProductDTO productDTO) {
		CategoryDTO categoryDTO = categoryService.findExactlyName(productDTO.getCategoryName());
		if(categoryDTO == null) {
			return new ResponseEntity<>("Category is invalid",HttpStatus.CONFLICT);
		}
		Product product = productService.convertFromDto(productDTO, categoryDTO);
		productService.saveProduct(product);
		return new ResponseEntity<>("Da them thanh cong",HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDTO productDTO) {
		ProductDTO foundProductDTO = productService.findById(id);
		if(foundProductDTO == null) {
			return new ResponseEntity<>("this id is invalid", HttpStatus.NOT_FOUND);
		}
		
		foundProductDTO.setCategoryName(productDTO.getCategoryName());
		foundProductDTO.setProductName(productDTO.getProductName());
		foundProductDTO.setDescription(productDTO.getDescription());
		foundProductDTO.setImage(productDTO.getImage());
		foundProductDTO.setPrice(productDTO.getPrice());
		foundProductDTO.setQuantity(productDTO.getQuantity());
		
		
		Product product = productService.convertFromDto(foundProductDTO, categoryService.findExactlyName(productDTO.getCategoryName()));
		product.setId(foundProductDTO.getId());
		product.setCreated_at(foundProductDTO.getCreated_at());
		
		productService.saveProduct(product);
		
		return new ResponseEntity<>("Da update thanh cong", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") Integer id) {
		if(productService.findById(id) == null) {
			return new ResponseEntity<>("id is invalid",HttpStatus.NOT_FOUND);
		}
		
		productService.deleteProduct(id);
		return new ResponseEntity<>("Da xoa thanh cong", HttpStatus.OK);
	}
	
}
