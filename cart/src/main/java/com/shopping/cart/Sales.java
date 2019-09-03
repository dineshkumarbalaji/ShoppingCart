package com.shopping.cart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;


import com.shopping.pojo.Product;
import com.shopping.servicImp.InputCartImp;
import com.shopping.servicImp.ShoppingCartImpl;
import com.shopping.service.InputCart;
import com.shopping.service.ShoppingCart;

public class Sales {
	static int totalprodut = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(System.in)));
		int choice = 0;
		String name, purchasedate;
		Map map = new HashMap();
		List<Product> itemList = new ArrayList();
		InputCart inputCart = new InputCartImp();
		inputCart.addProduct(new Product("101", "soup", "tin", 0.65, 100));
		inputCart.addProduct(new Product("102", "milk", "bottle", 1.30, 100));
		inputCart.addProduct(new Product("103", "bread", "loaf", 0.80, 100));
		inputCart.addProduct(new Product("104", "apple", "single", 0.10, 100));

	//	inputCart.getCartDetails().stream().forEach(e -> System.out.println(e));

		do {

			System.out.println("Enter you choise");
			System.out.println("1 :: Add purchase order");
			System.out.println("2 :: To view Stock deatils");
			System.out.println("3 :: sales Billing");
			System.out.println("0 :: To exit");

			try {
				choice = Integer.parseInt((bufferedReader.readLine().trim()));
			} catch (NumberFormatException ex) {
				choice = 5;
				System.out.println("Enter a valid number");
			}
			if (choice == 0)
				break;

			switch (choice) {
			case 1:
				System.out.println("Enter the Number of product to be added");
				int quantity = 0;
				try {
					quantity = Integer.parseInt((bufferedReader.readLine().trim()));
				} catch (NumberFormatException ex) {
					System.out.println("Enter a valid number");
				}
				IntStream.range(0, quantity).forEach(i -> {
					try {

						String[] strs = bufferedReader.readLine().trim().split("\\s+");
						// System.out.println(strs[0]+"\t"+strs[1]+"\t"+strs[2]);
						Product pojo = new Product();
						pojo.setPname(strs[0].toLowerCase());
						pojo.setCost(Double.parseDouble(strs[1]));
						pojo.setQuantity(Integer.parseInt(strs[2]));
						pojo.setPunit(strs[3].toLowerCase());
						inputCart.addProduct(pojo);

					} catch (IOException ex) {
						throw new RuntimeException(ex);

					}
				});
				break;
			case 2:
				inputCart.getCartDetails().stream().forEach(e -> System.out.println(e));
				break;
			case 3:

				System.out.println("Please Enter the name of the user");
				name = bufferedReader.readLine();

				System.out.println("Enter the Date of purchase in DD/mm/YYYY format");
				purchasedate = bufferedReader.readLine();
				if (isValidFormat(purchasedate)) {
					System.out.println("Enter the Number of product to be added");
					System.out.println();
					ShoppingCart sc = new ShoppingCartImpl();
					Map<String,Product> Productdeatils = inputCart.getProductName();
					
					try {
						totalprodut = Integer.parseInt((bufferedReader.readLine().trim()));
					} catch (NumberFormatException ex) {
						System.out.println("Enter a valid number");
					}
					System.out.println("Enter the Product name and quantity Eg : Apple 3");
					System.out.println();
					for (int i = 0; i < totalprodut; i++) {


						try {

							String[] strs = bufferedReader.readLine().trim().split("\\s+");
							Product pojo = new Product();
							pojo.setPname(strs[0].toLowerCase());
							pojo.setQuantity(Integer.parseInt(strs[1]));
							try {
								sc.addPurchaseItem(pojo, Productdeatils.keySet());

							} catch (ProductNotFoundException e1) {
								System.out.println(e1.getMessage());
								i--;
							}

						} catch (IOException ex) {
							throw new RuntimeException(ex);

						}

					}
					
					try {
						System.out.println("Hi "+name+" your bill amount for the date of purchase"+purchasedate);
						System.out.println();
						sc.getBillamount(Productdeatils,purchasedate);
						
					} catch (ParseException e1) {
						
						e1.printStackTrace();
					}
					
					
				} else {
					System.out.println("Enter the valid date");
				}
				break;
			default:
				break;
			}

		} while (choice > 0);

		System.out.println("Thanks for using Billing soft");

	}

	public static boolean isValidFormat(String value) {
		LocalDateTime ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);

		try {
			ldt = LocalDateTime.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, fomatter);
				String result = ld.format(fomatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, fomatter);
					String result = lt.format(fomatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
					System.out.println("Data format error");
				}
			}
		}

		return false;
	}

}
