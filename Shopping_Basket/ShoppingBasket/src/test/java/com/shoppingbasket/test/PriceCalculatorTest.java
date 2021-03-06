package com.shoppingbasket.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.shoppingbasket.dto.Bill;
import com.shoppingbasket.services.PriceCalculator;

class PriceCalculatorTest {

	@Test
	void discountOrderonApple() {

		String[] input = { "Apples", "Milk", "Bread" };
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
	void discountOrderOnSoup() {

		String[] input = { "Soup", "Soup", "Milk", "Bread", "Apples" };
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

	@Test
	void discountOrderOnMultipleSoup() {

		String[] input = ("Soup Soup Soup Soup Soup Milk Milk Milk Milk Bread Bread Bread Bread Bread Apples Apples Apples")
				.split(" ");
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
		assertEquals(15.45, bill.getSubTotal(), 0.1, "Subtotal mismatched");

		// Total
		assertEquals(14.35, bill.getTotal(), 0.1, "Total mismatched");

	}

	@Test
	void discountOrderNoDiscount() {

		String[] input = ("Soup Milk Milk Bread").split(" ");
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
		assertEquals(4.05, bill.getSubTotal(), 0.1, "Subtotal mismatched");

		// Total
		assertEquals(4.05, bill.getTotal(), 0.1, "Total mismatched");

	}

	@Test
	void invalidItemsInPurchaseOrder() {

		String[] input = ("Soup Soap Milk Bread").split(" ");
		PriceCalculator priceCalc = new PriceCalculator();

		// Valid Bill
		assertThrows(Exception.class, () -> priceCalc.setOrders(input), "Failure to through exception");

	}

}
