package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getAll(){
		return  productRepository.findAll();
	}
	
	public Optional<Product> ViewProById(int id) {

		return productRepository.findById(id);
		
	}
	
	public List<Product> viewProsByType(String type) {
		return productRepository.findAllByType(type);
	}
	
	public List<Product> findProByName(String name){
		
		return productRepository.findProductByName(name);
	}
}
