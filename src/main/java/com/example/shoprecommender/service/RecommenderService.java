package com.example.shoprecommender.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoprecommender.dto.Product;
import com.example.shoprecommender.dto.Purchase;
import com.example.shoprecommender.dto.User;

@Service
public class RecommenderService {
	@Autowired
	private PurchaseService pService;
	@Autowired
	private CatalogService cService;
	
	public List<Product> popularProduct(String productId, String userId){
		List<User> usersBuyers = pService.getUsersByProduct(productId);
		
		if(usersBuyers.size() > 0) {
			Map<String,	Integer> tmpMap = new HashMap<String, Integer>();
			
			for (User u : usersBuyers) {
				List<Purchase> userPurchases = pService.getPurchaseByUserId(u.getUserId());
				
				for(Purchase p : userPurchases) {
					if(!p.getProductId().equals(productId)) {
						if(tmpMap.get(p.getProductId()) == null) {
							tmpMap.put(p.getProductId(), 1);
						} else {
							Integer iTmp = tmpMap.get(p.getProductId());
							iTmp++;
							tmpMap.replace(p.getProductId(), iTmp);
						}
					}
				}
			}
			
			List<Product> reco = new ArrayList<Product>();
			
			//Sorting Map
			tmpMap.entrySet()
			    .stream()
			    .sorted((x, y) -> y.getValue().compareTo(x.getValue()))
			    .limit(5)
			    .forEach(x -> reco.add(cService.getProduct(x.getKey())) );
			
			return reco;
		} else {
			return new ArrayList<Product>();
		}
	}
}
