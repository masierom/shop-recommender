package com.example.shoprecommender.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoprecommender.dto.Product;

@Service
public class RecommenderService {
	@Autowired
	private PurchaseService pService;
	
	public List<Product> popularProduct(String productId, String userId){
		//List<User> usersBuyers = pService.getUsersByProduct(productId);
		
		return null;
	}
}
