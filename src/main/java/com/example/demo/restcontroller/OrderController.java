package com.example.demo.restcontroller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.entity.Order_Product;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.OrderListService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class OrderController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderListService orderListService;
	
	@RequestMapping(value = "/test")
	public void test(@RequestBody String str) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode data = objectMapper.readValue(str, JsonNode.class);
//			System.out.println(data);
			if(data != null) {
				int payment = data.get("pay").asInt();
				String name = data.get("cusName").asText();
				String email = data.get("email").asText();
				String phone = data.get("phone").asText();
				String address = data.get("address").asText();
				JsonNode bill = data.get("bill");
				Order order = new Order();
				order.setName(name);
				order.setEmail(email);
				order.setPhone(phone);
				order.setAddress(address);
				order.setTotalPayment(payment);
				orderService.save(order);
				int quantity = 0;
				int price = 0;
				for (JsonNode jsonNode : bill) {
					Order_Product order_Product = new Order_Product();
					System.out.println(jsonNode);
					int productId = jsonNode.get("id").asInt();
					Product product = productRepository.findById(productId).orElse(null);
					product.setQuantity(jsonNode.get("buyQuantity").asInt());
					System.out.println(product.getPrice() + " " + product.getQuantity());
					quantity+=product.getQuantity();
					price+=product.getPrice();
					
					order_Product.setOrder(order);
					order_Product.setProduct(product);
					order_Product.setQuantity(quantity);
					order_Product.setPrice(price);
					System.out.println(order_Product);
					orderListService.saveBill(order_Product);
				}
				
				User user = new User();
				user.setName(name);
				user.setEmail(email);
				user.setPhone(phone);
				user.setAddress(address);
				userService.saveUser(user);
			}
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
