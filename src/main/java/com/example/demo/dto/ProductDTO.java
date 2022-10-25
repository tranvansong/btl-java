package com.example.demo.dto;

import com.example.demo.entity.Product;

public class ProductDTO {
	private Integer id;
	private String categoryName;
	private Integer categoryId;
	private String productName;
	private String description;
	private String image;
	private int price;
	private int quantity;
	
	
	public ProductDTO() {
	}
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.categoryName = product.getCategory().getName();
		this.categoryId = product.getCategory().getId();
		this.productName = product.getName();
		this.description = product.getDescription();
		this.image = product.getImage();
		this.price = product.getPrice();
		this.quantity = product.getQuantity();
	}
	
	public ProductDTO(String categoryName, String productName, String description, String image, int price,
			int quantity) {
		this.categoryName = categoryName;
		this.productName = productName;
		this.description = description;
		this.image = image;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", categoryName=" + categoryName + ", productName=" + productName
				+ ", description=" + description + ", image=" + image + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
	
	
}
