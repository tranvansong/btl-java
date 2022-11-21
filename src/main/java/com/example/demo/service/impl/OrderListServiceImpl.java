package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.entity.Order_Product;
import com.example.demo.entity.Product;
import com.example.demo.repository.Order_ProductRepository;
import com.example.demo.service.OrderListService;

@Service
public class OrderListServiceImpl implements OrderListService {

	@Autowired
	private Order_ProductRepository orderListRepository;
	
	@Override
	public List<Order_Product> findByOrder_id(int id) {
		return orderListRepository.findByOrder_id(id);
	}

	@Override
	public List<Product> findProductsByOrder_id(int id) {
		List<Product> products = new ArrayList<>();
		List<Order_Product> list = this.findByOrder_id(id);
		for (Order_Product order_Product : list) {
			products.add(order_Product.getProduct());
		}
		return products;
	}

	@Override
	public List<Order_Product> findAll() {
		return orderListRepository.findAll();
	}

	@Override
	public Order findOrderById(int id) {
		List<Order_Product> list = this.findByOrder_id(id);
		return list.get(0).getOrder();
	}

	@Override
	public List<Order> findAllOrders() {
		List<Order> orders = new ArrayList<>();
		List<Order_Product> list = this.findAll();
		for (Order_Product order_Product : list) {
			if(!orders.contains(order_Product.getOrder())) orders.add(order_Product.getOrder());
		}
		return orders;
	}

	@Override
	public void saveBill(Order_Product order_Product) {
		orderListRepository.save(order_Product);
	}

	@Override
	public long earnmoneythismonth() {
		long money = 0;
		
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (Order order: this.findAllOrders()) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(order.getCreated_at());
			int month = cal.get(Calendar.MONTH)+1;
			int year = cal.get(Calendar.YEAR);
			System.out.println(order.getTotalPayment());
			if(month == currentMonth && year == currentYear) money+=order.getTotalPayment();
		}
		
		return money;
	}
	
	
}
