package com.example.demo.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public List<ProductDTO> findAll() {
		List<Product> list = productRepository.findAll();
		List<ProductDTO> listDtos = new ArrayList<>();
		for (Product product : list) {
			ProductDTO productDTO = new ProductDTO(product);
			listDtos.add(productDTO);
		}
		return listDtos;
	}

	@Override
	public ProductDTO findById(Integer id) {
		Product foundProduct = productRepository.findById(id).orElse(null);
		ProductDTO productDTO = new ProductDTO(foundProduct);
		return productDTO;
	}

	@Override
	public List<ProductDTO> findByName(String name) {
		List<Product> list = productRepository.findByName(name);
		List<ProductDTO> listDtos = new ArrayList<>();
		for (Product product : list) {
			ProductDTO productDTO = new ProductDTO(product);
			listDtos.add(productDTO);
		}
		return listDtos;
	}

	@Override
	public List<ProductDTO> filterByPrice(int min, int max) {
		List<Product> list = productRepository.filterByPrice(min, max);
		List<ProductDTO> listDtos = new ArrayList<>();
		for (Product product : list) {
			listDtos.add(new ProductDTO(product));
		}
		return listDtos;
	}

	@Override
	public List<ProductDTO> findProductsByCategoryName(String categoryName) {
		List<Product> list = productRepository.findAll();
		List<ProductDTO> listProductDTOs = new ArrayList<>();
		for (Product product : list) {
			if(product.getCategory().getName().toLowerCase().equals(categoryName.toLowerCase())) {
				listProductDTOs.add(new ProductDTO(product));
			}
		}
		return listProductDTOs;
	}
}
