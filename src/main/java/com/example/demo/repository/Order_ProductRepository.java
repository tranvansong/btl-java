package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.Order_Product;
import com.example.demo.entity.Product;

@Repository
public interface Order_ProductRepository extends JpaRepository<Order_Product, Integer> {
	List<Order_Product> findByOrder_id(int id);
//	List<Product> findProductsByOrder_id(int id);
//	List<Order> findOrderById(int id);
}
