package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.OrderInfo;
import com.example.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

//	@Query("SELECT new OrderInfo.class.getName()"
//			+ "(ord.id, ord.created_at, ord.totalpayment,"
//			+ "ord.name, ord.address, ord.email, ord.phone)" + " from"
//			+ "Order.class.getName() ")
//	List<OrderInfo> listOrderInfos();
}
