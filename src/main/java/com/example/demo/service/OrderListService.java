package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Order;
import com.example.demo.entity.Order_Product;
import com.example.demo.entity.Product;

public interface OrderListService {
	public List<Order_Product> findAll();
	public List<Order_Product> findByOrder_id(int id);
	public List<Product> findProductsByOrder_id(int id);
	public Order findOrderById(int id);
	public List<Order> findAllOrders();
	public void saveBill(Order_Product order_Product);
	public long earnmoneythismonth();
}
