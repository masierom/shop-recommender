package com.example.shoprecommender.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.shoprecommender.dto.Product;
import com.example.shoprecommender.service.RecommenderService;

@Controller
public class RecommenderController {
	@Autowired
	private RecommenderService service;
	
	@GetMapping("/api/reccomendations/{productId}/{userId}")
	public @ResponseBody List<Product> popularProduct(@PathVariable String productId, @PathVariable String userId){
		return service.popularProduct(productId, userId);
	}
}
