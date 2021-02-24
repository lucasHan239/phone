package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public void insertOrder(Order order) {
		orderRepository.insertOrder(
				order.getIdUser(),
				order.getCart().getId(),
				order.getNameEmp(),
				order.getTelEmp(),
				order.getAddressEmp(),
				order.getCityEmp());
	}
	
	public List<Order> findAllOrder(int idUser){
		return orderRepository.findAllByIdUser(idUser);
	}
	
}
