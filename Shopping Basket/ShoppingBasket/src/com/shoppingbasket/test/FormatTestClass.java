package com.shoppingbasket.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import com.shoppingbasket.dto.Bill;
import com.shoppingbasket.services.BillWithDiscountFormatter;
import com.shoppingbasket.services.BillWithoutDiscountFormatter;
import com.shoppingbasket.services.Formatter;
import com.shoppingbasket.services.PriceCalculator;

class FormatTestClass {

	@Test
	void checkformatForDiscountOrderOnApple() {

		String[] input = { "Apples", "Milk", "Bread" };
		PriceCalculator priceCalc = new PriceCalculator();
		Bill bill = null;
		try {
			bill = priceCalc.setOrders(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Valid Bill
		assertDoesNotThrow(() -> priceCalc.setOrders(input));

		Formatter formatter = new BillWithDiscountFormatter();

		String validResponse = ("Subtotal: £3.10\n" + "Apples 10% off: -­10p\n" + "Total: £3.00");

		// Format checking
		assertArrayEquals(formatter.formatBill(bill).getBytes(), validResponse.getBytes());

	}
	
	@Test
	void checkformatWithNoDiscount() {

		String[] input = {"Milk", "Bread" };
		PriceCalculator priceCalc = new PriceCalculator();
		Bill bill = null;
		try {
			bill = priceCalc.setOrders(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Valid Bill
		assertDoesNotThrow(() -> priceCalc.setOrders(input));

		Formatter formatter = new BillWithoutDiscountFormatter();

		String validResponse = ("Subtotal: £2.10\n" + "(No offers available)\n" + "Total price: £2.10");

		// Format checking
		assertArrayEquals(formatter.formatBill(bill).getBytes(), validResponse.getBytes());

	}

}
