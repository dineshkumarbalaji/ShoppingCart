package com.shopping.servicImp;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.shopping.cart.ProductNotFoundException;
import com.shopping.pojo.Product;
import com.shopping.service.ShoppingCart;

public class ShoppingCartImpl implements ShoppingCart {

	private static Map<String, Integer> cartmap;
    DecimalFormat df = new DecimalFormat("##.00");
	static Date todayDate, yesterdayDate, tillNextSevenDays, fromThreeDays, lastDayOfTheMonth;

	public static void dates() {

		Date today = new Date();
		Calendar calender = Calendar.getInstance();
		todayDate = calender.getTime();

		Calendar calender1 = Calendar.getInstance();
		calender1.add(Calendar.DATE, -1);
		yesterdayDate = calender1.getTime();

		Calendar calender2 = Calendar.getInstance();
		calender2.add(Calendar.DATE, +7);
		tillNextSevenDays = calender2.getTime();

		Calendar calender3 = Calendar.getInstance();
		calender3.add(Calendar.DATE, +3);
		fromThreeDays = calender3.getTime();

		Calendar calender4 = Calendar.getInstance();
		calender4.setTime(today);
		calender4.add(Calendar.MONTH, 1);
		calender4.set(Calendar.DAY_OF_MONTH, 1);
		calender4.add(Calendar.DATE, -1);
		lastDayOfTheMonth = calender4.getTime();

	

	} // dates

	public ShoppingCartImpl() {

		cartmap = new HashMap<String, Integer>();
	}

	public Map addPurchaseItem(Product sp, Set set) throws ProductNotFoundException {

		if (set.contains(sp.getPname())) {
			if (cartmap.containsKey(sp.getPname())) {
				cartmap.put(sp.getPname(), sp.getQuantity() + sp.getQuantity());
			} else {
				cartmap.put(sp.getPname(), sp.getQuantity());

			}

			return cartmap;
		} else
			throw new ProductNotFoundException("Product with Name " + sp.getPname() + " is not Found.");
	}

	public Map RemovePurchaseItem(String pname) {
		if (cartmap.containsKey(pname)) {
			cartmap.remove(pname);
		}

		return cartmap;
	}


	public Map getShoppingCartDetails() {
		return cartmap;
	}

	public Double getBillamount(Map<String, Product> map, String purchasedate) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(purchasedate);
		dates();
		
		
		Double totalPrice = 0.00;
		Double soupPrice = 0.00;
		Double breadPrice = 0.00;
		Double milkPrice = 0.00;
		Double applePrice = 0.00;
		if(cartmap.containsKey("soup") && cartmap.get("soup")> 0) {
			soupPrice = Double.valueOf(df.format(cartmap.get("soup")*map.get("soup").getCost()));
		}// if - noOfSoupTins
		if(cartmap.containsKey("bread") && cartmap.get("bread") > 0) {
			
				if(date.after(yesterdayDate) && date.before(tillNextSevenDays)) {
					int offerAvailableForNoOFBreads = cartmap.get("soup")/2;
					int NoofferBreads = cartmap.get("bread") - offerAvailableForNoOFBreads ;
					
				
					if(offerAvailableForNoOFBreads>0) {
						
						double discountprice =0.00;
						discountprice = map.get("bread").getCost()/2;
						breadPrice = Double.valueOf(df.format(offerAvailableForNoOFBreads*discountprice));
					}
					breadPrice += Double.valueOf(df.format(NoofferBreads*map.get("bread").getCost()));
				}else{
					breadPrice =Double.valueOf(df.format(cartmap.get("bread")*map.get("bread").getCost())) ;		
				}
		} // if - noOfBreadLoafs
		
		if(cartmap.containsKey("milk") && cartmap.get("milk")>0) {
			
			milkPrice =Double.valueOf(df.format(cartmap.get("milk")*map.get("milk").getCost())) ;	
		}// if noOfMilkBottles
		
		if(cartmap.containsKey("apple") && cartmap.get("apple")>0) {
			
			if(date.after(fromThreeDays) && date.before(lastDayOfTheMonth)) {
				double discount= 0.0;
				discount = 100 - 10;
				applePrice = Double.valueOf(df.format((discount* (cartmap.get("apple")*map.get("apple").getCost()))/100));
				
			}else{
				applePrice =Double.valueOf(df.format(cartmap.get("apple")*map.get("apple").getCost())) ;	
			}
			
		} //if noOfApples
		
		totalPrice = soupPrice+breadPrice+milkPrice+applePrice;
		System.out.printf("%.2f", totalPrice);
		System.out.println();

		return totalPrice;
	}
	
	

}
