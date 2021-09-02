package com.example.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.web.dao.BasketDAO;
import com.example.web.vo.Basket;
@Service
public class BasketService {
	
	@Autowired
	BasketDAO basketDAO;
	
	public void basketinsert(Basket b) throws Exception{
		Integer quantity=basketDAO.basketSelect(b);
		System.out.println(quantity);
		if(quantity==null) {
			b.setQuantity(1);
			basketDAO.basketinsert(b);
		}else {
			b.setQuantity(quantity.intValue()+1);
			basketDAO.basketUpdate(b);
		}
		
		
		
	}

}
