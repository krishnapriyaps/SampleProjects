package com.shoppingbasket.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.shoppingbasket.dto.Bill;
import com.shoppingbasket.services.PriceCalculator;

class PriceCalculatorTest {

	@Test
	void testCase1() {
		  
		String[]  input = {"Apples","Milk","Bread"};
		PriceCalculator priceCalc = new PriceCalculator();
		Bill bill = null;
		try {
			bill = priceCalc.setOrders(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Valid Bill
		assertNotNull("Bill creation failure", bill);
		// Subtotal
		assertEquals(3.10, bill.getSubTotal(), 0.1, "Subtotal matched");
		// Total
		assertEquals(3.00, bill.getTotal(), 0.1, "Total matched");
		
		
	}
	
	@Test
	void testCase2() {

		String[]  input = {"Soup","Soup", "Milk","Bread", "Apples"};
		PriceCalculator priceCalc = new PriceCalculator();
		Bill bill = null;
		try {
			bill = priceCalc.setOrders(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Valid Bill
		assertNotNull("Bill creation failure", bill);
		
		// Subtotal
		assertEquals(4.4, bill.getSubTotal(), 0.1, "Subtotal mismatched");
		
		// Total
		assertEquals(3.9, bill.getTotal(), 0.1, "Total mismatched");
		
	}

}
