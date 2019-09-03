package com.shopping.service;

import java.text.ParseException;
import java.util.Map;
import java.util.Set;

import com.shopping.cart.ProductNotFoundException;
import com.shopping.pojo.Product;

public interface ShoppingCart {
	
	public Map addPurchaseItem(Product shoppingproduct,Set set) throws ProductNotFoundException;
	public Map RemovePurchaseItem(String pname);	
	public Map getShoppingCartDetails();
	public Double getBillamount(Map<String,Product> map,String purchasedate) throws ParseException;
}
