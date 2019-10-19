package com.eshopping.product.dashboard.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junit.framework.Assert;

public class PostFxTest {
	
	PostFixCalculator postFixCalculator= new PostFixCalculator();
	
	@Test
	public void testAddition() {
		//run this test case it will fail first as there is not implementation
		//then go to actual class start implementing the logic.
		String exp = "93+"; 
		assertEquals(12,postFixCalculator.evaluatePostfix(exp));		
	}

}
