package com.example.demo.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

public interface ProductService {
	public List<Product> findAllProducts();
	public List<ProductDTO> findAll();
	public ProductDTO findById(Integer id);
	public List<ProductDTO> findByName(String name); // tim kiem theo ten
	public List<ProductDTO> filterByPrice(int min, int max);
	public List<ProductDTO> findProductsByCategoryName(String categoryName);
	public void saveProduct(Product product);
	public Product convertFromDto(ProductDTO productDTO, CategoryDTO categoryDTO);
	public void deleteProduct(Integer id);
	public void saveProduct(String name, String description, int quantity, int price, Category category, MultipartFile file);
	public void updateProduct(Integer id, String name, String description, int quantity, int price, Category category, MultipartFile file);
	public String changefileToString(MultipartFile file);
	public boolean isDuplicatedName(String name);
	public List<ProductDTO> findByKeyword(String keyword);
}
