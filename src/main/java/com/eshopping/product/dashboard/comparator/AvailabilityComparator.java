package com.eshopping.product.dashboard.comparator;

import java.util.Comparator;

import com.eshopping.product.dashboard.model.Product;

public class AvailabilityComparator implements Comparator<Product> {
	/**
	* Comparator used for sorting of availability
	*
	* @param product1
	*
	* @param product2
	*
	* @return ResponseEntity - HTTP response
	*/
	@Override
	   public int compare(Product product1, Product product2) {
	       return Boolean.valueOf(product2.getAvailability()).compareTo(Boolean.valueOf(product1.getAvailability()));
	   }

}
