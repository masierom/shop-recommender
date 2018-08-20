package com.example.shoprecommender.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shoprecommender.dto.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CatalogService {
	@Autowired
	private RestTemplate rt;
	
	@HystrixCommand(fallbackMethod="defaultProductList")
	public List<Product> getAllProducts(){
		Product[] vProduct = rt.getForObject(
				"http://shop-catalog/api/products",
				Product[].class);
		
		List<Product> allProduct = new ArrayList<Product>();
		allProduct.addAll(Arrays.asList(vProduct));
		
		return allProduct;
	}
	
	@HystrixCommand(fallbackMethod="defaultProduct")
	public Product getProduct(String productId){
		return rt.getForObject(
				"http://shop-catalog/api/products/{productId}",
				Product.class, productId);
	}
	
	public List<Product> defaultProductList(){
		return new ArrayList<Product>();
	}
	
	public Product defaultProduct(String productId) {
		Product product = new Product();
		product.setId("0000");
		product.setTitle("Unavaible");
		product.setAvailability(0);
		return product;
	}
}
