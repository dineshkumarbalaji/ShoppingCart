package com.shopping.cart;

public class ProductNotFoundException extends Exception {
	public ProductNotFoundException(){}
	public ProductNotFoundException(String msg){
		super(msg);
	}

}
