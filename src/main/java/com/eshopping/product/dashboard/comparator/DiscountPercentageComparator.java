package com.eshopping.product.dashboard.comparator;

import java.util.Comparator;

import com.eshopping.product.dashboard.model.Product;

public class DiscountPercentageComparator implements Comparator<Product>{
	
	/**
	* Comparator used for sorting of discount percentage in descending order
	*
	* @param product1
	*
	* @param product2
	*
	* @return ResponseEntity - HTTP response
	*/
	@Override
	   public int compare(Product product1, Product product2) {
	       double discountPercentageForProduct1 = (product1.getRetailPrice() - product1.getDiscountedPrice())/product1.getRetailPrice() * 100;
	       double discountPercentageForProduct2 = (product2.getRetailPrice() - product2.getDiscountedPrice())/product2.getRetailPrice() * 100;
	       int flag=(int) (Math.round(discountPercentageForProduct2)-Math.round(discountPercentageForProduct1));
	       return flag;
	   }

}
