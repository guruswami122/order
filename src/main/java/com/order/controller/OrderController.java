package com.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.dto.Order;

@RestController
public class OrderController {
	
	@Autowired
	Environment environment;
	
	@GetMapping("/port")
	public String getPortNo() {
		String port = environment.getProperty("local.server.port");
		return "From Order app : " + port;
	}
	
	static Map<String, List<Order>> userOrders = new HashMap<>();
	
	static {
		
		List<Order> orders = new ArrayList<>();
		Order order=new Order();
		order.setId(100);
		order.setDescription("100Description");
		orders.add(order);
	
		userOrders.put("100", orders);
		
		orders = new ArrayList<>();
		
		order=new Order();
		order.setId(300);
		order.setDescription("300Description");
		orders.add(order);
		userOrders.put("300", orders);
		
	}
	
	@GetMapping("/{userId}")
	public List<Order> getAllById(@PathVariable String userId) {
		return userOrders.get(userId);
	}
	
	@GetMapping("/allOrders")
	public Map<String, List<Order>> getAll() {
		return userOrders;
	}
	@GetMapping("/byparam")
	public List<Order> getAllByReqParam(@RequestParam String userId) {
		return userOrders.get(userId);
	}

}
