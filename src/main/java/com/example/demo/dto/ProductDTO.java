package com.example.demo.dto;

import java.util.*;

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
	private Date created_at;
	private Date updated_at;
	private String slug;
	private String categorySlug;
	
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
		this.created_at = product.getCreated_at();
		this.updated_at = product.getUpdated_at();
		this.slug = doiten(product.getName());
		this.categorySlug = doiten(product.getCategory().getName());
	}
	
	public ProductDTO(String categoryName, String productName, String description, String image, int price,
			int quantity, Date created_at, Date updated_at) {
		this.categoryName = categoryName;
		this.productName = productName;
		this.description = description;
		this.image = image;
		this.price = price;
		this.quantity = quantity;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.slug = doiten(productName);
		this.categorySlug = doiten(categoryName);
	}
	
	public ProductDTO(Integer id, String productName, int price, int quantity) {
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	public String doiten(String s) {
		StringTokenizer stringTokenizer = new StringTokenizer(s);
		StringBuilder builder = new StringBuilder();
		while(stringTokenizer.hasMoreTokens()) {
			String t = stringTokenizer.nextToken();
			builder.append(t.toLowerCase());
		}
		return builder.toString();
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
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	public String getSlug() {
		return slug;
	}
	
	public String getCategorySlug() {
		return categorySlug;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", productName=" + productName + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
//	
//	@Override
//	public String toString() {
//		return "ProductDTO [id=" + id + ", categoryName=" + categoryName + ", categoryId=" + categoryId
//				+ ", productName=" + productName + ", description=" + description + ", image=" + image + ", price="
//				+ price + ", quantity=" + quantity + ", created_at=" + created_at + ", updated_at=" + updated_at
//				+ ", slug=" + slug + "]";
//	}
	
	
}
