package com.shopping.servicImp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.shopping.cart.ProductNotFoundException;
import com.shopping.pojo.Product;
import com.shopping.service.InputCart;
public class InputCartImp implements InputCart {

	
	private Map map;
	
	
	public InputCartImp(){
		
		map = new HashMap<String,Product>();
		
	}

	public boolean addProduct(Product p) {
		if(map.containsKey(p.getPname().toLowerCase())){
			Product p1 = (Product) map.get(p.getPname().toLowerCase());
			p1.setCost(p.getCost());
			p1.setQuantity(p1.getQuantity()+p.getQuantity());
			
			return true;
		}
		map.put(p.getPname().toLowerCase(),p);
		
		return false;
	}

	public boolean removeProduct(String Pname) throws ProductNotFoundException {
		if(map.containsKey(Pname.toLowerCase())){
			map.remove(Pname);
			return true;
		}else throw new ProductNotFoundException(
      "Product with Name "+Pname+" is not Found.");
	}

	public Collection<Product> getCartDetails() {
			return map.values();
	}
	
	public Map getProductName() {
		return map;
	}
	
	

}
