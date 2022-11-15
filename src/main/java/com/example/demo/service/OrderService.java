package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Order;

public interface OrderService {
	public void save(Order order);
	public List<Order> getAllOrders();
}
