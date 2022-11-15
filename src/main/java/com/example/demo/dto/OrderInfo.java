package com.example.demo.dto;

import java.util.List;

public class OrderInfo {
	private int id;
	private String customerName;
	private String customerEmail;
	private String customerAddress;
	private String customerPhone;
	private int totalPayment;
	
	private List<ProductDTO> products;
	
	public OrderInfo() {}
	
	public OrderInfo(int id, String customerName, String customerEmail, String customerAddress, String customerPhone,
			int totalPayment, List<ProductDTO> products) {
		this.id = id;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.totalPayment = totalPayment;
		this.products = products;
	}

	public OrderInfo(int id, String customerName, String customerEmail, String customerAddress, String customerPhone,
			int totalPayment) {
		this.id = id;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.totalPayment = totalPayment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public int getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
}
