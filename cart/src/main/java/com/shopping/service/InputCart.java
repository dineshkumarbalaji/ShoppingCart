package com.shopping.service;

import java.util.Collection;
import java.util.Map;

import com.shopping.cart.ProductNotFoundException;
import com.shopping.pojo.Product;



public interface InputCart {
	
	boolean addProduct(Product p);
	boolean removeProduct(String pid) throws ProductNotFoundException;
	Collection getCartDetails();
	Map<String,Product> getProductName();
 

}
