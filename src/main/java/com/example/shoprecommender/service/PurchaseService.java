package com.example.shoprecommender.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shoprecommender.dto.Purchase;
import com.example.shoprecommender.dto.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PurchaseService {
	@Autowired
	private RestTemplate rt;
	
	@HystrixCommand(fallbackMethod="defaultUserList")
	public List<User> getUsersByProduct(String productId){
		User[] vUsers = rt.getForObject(
				"http://shop-purchase/api/purchase/users/{productId}",
				User[].class, productId);
		
		List<User> users = new ArrayList<User>();
		users.addAll(Arrays.asList(vUsers));
		
		return users;
	}
	
	@HystrixCommand(fallbackMethod="defaultPurchaseList")
	public List<Purchase> getPurchaseByUserId(String userId) {
		Purchase[] vPurch = rt.getForObject(
				"http://shop-purchase/api/purchase/{userId}",
				Purchase[].class, userId);
		
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchases.addAll(Arrays.asList(vPurch));
		
		return purchases;
	}
	
	public List<User> defaultUserList(String productId){
		return new ArrayList<User>();
	}
	
	public List<Purchase> defaultPurchaseList(String productId){
		return new ArrayList<Purchase>();
	}
}
