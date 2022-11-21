package com.example.demo.service.impl;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
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

	@Override
	public void saveProduct(String name, String description, int quantity, int price, Category category, MultipartFile file) {
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setCategory(category);
		
		product.setImage(changefileToString(file));
		productRepository.save(product);
	}

	@Override
	public Product convertFromDto(ProductDTO productDTO, CategoryDTO categoryDTO) {
		Product product = new Product(productDTO.getProductName(), productDTO.getDescription(), categoryService.convertFromCategoryDTO(categoryDTO), productDTO.getQuantity(), productDTO.getPrice(), productDTO.getImage());
		product.setId(productDTO.getId());
		return product;
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void updateProduct(Integer id, String name, String description, int quantity, int price, Category category,
			MultipartFile file) {
		Product product = productRepository.findById(id).orElse(null);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setCategory(category);
		product.setImage(changefileToString(file));
		productRepository.save(product);
	}
	
	@Override
	public String changefileToString(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("Not valid file");
		}
		try {
			return Base64.getEncoder().encodeToString(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0000";
		}
	}

	@Override
	public boolean isDuplicatedName(String name) {
		List<ProductDTO> list = this.findAll();
		ProductDTO found = list.stream().filter(c -> c.getProductName().toLowerCase().equals(name.toLowerCase())).findFirst().orElse(null);
		return found != null;
	}

	@Override
	public List<ProductDTO> findByKeyword(String keyword) {
		List<Product> list = productRepository.findByKeyword(keyword);
		List<ProductDTO> listDtos = new ArrayList<>();
		for (Product product : list) {
			ProductDTO productDTO = new ProductDTO(product);
			listDtos.add(productDTO);
		}
		return listDtos;
	}
	
}
