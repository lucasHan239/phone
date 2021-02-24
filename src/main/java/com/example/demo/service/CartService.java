package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItemRepository;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	public Cart getCart(int idUser) {
		List<Integer> ids = cartRepository.findMaxId(idUser);
		int size = ids.size();
		if (size < 1) {
			insertNewCart(idUser);
			return getCart(idUser);
		}
		Cart cart = cartRepository.findByIdAndIdUser(ids.get(size-1), idUser).get();
		
		return cart;
	}
	
	public void insertNewCart(int idUser) {
		cartRepository.insertNewCart(idUser);
	}
	
	public void insertItem(Item item) {
		
		itemRepository.insertItem(item.getProduct().getId(), 
				item.getQuantity(),
				item.getGuarantee(),
				item.getCart().getId());
	}
	
	
	public void deleteItem(int id) {
		System.out.println(itemRepository.deleteItemByID(id));
	}
	
	public Item getItem(int id) {
		return itemRepository.findById(id).get();
	}
	
	public void updateItem(Item item) {
		itemRepository.updateItem(item.getIdDetail(), item.getQuantity(), item.getGuarantee());
	}
}
