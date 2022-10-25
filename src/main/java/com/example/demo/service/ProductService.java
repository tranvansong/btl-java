package com.example.demo.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

public interface ProductService {
	public List<ProductDTO> findAll();
	public ProductDTO findById(Integer id);
	public List<ProductDTO> findByName(String name); // tim kiem theo ten
	public List<ProductDTO> filterByPrice(int min, int max);
	public List<ProductDTO> findProductsByCategoryName(String categoryName);
}
