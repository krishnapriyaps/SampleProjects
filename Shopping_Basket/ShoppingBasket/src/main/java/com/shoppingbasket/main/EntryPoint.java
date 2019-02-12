package com.shoppingbasket.main;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.shoppingbasket.dto.Bill;
import com.shoppingbasket.services.PriceCalculator;
import com.shoppingbasket.util.Constants;
import com.shoppingbasket.util.PrinterUtil;

/**
 * 
 * Shopping basket accepts a list of items and generates the price total
 * considering the discounts applicable to the order
 *
 */
public class EntryPoint {

	final static Logger logger = Logger.getLogger(EntryPoint.class);

	/**
	 * Accepts lists of items, prices and offers
	 * 
	 * @param <SourceType> <arguments as per selected source>
	 * 
	 *        Sample arguments : PriceBasket item1 item2 item3 ...
	 * 
	 */
	public static void main(String[] args) {
		logger.debug("Job Started ");

		/*
		 * Input Validations
		 */
		if (args == null || args.length == 0 || !Constants.PRICE_CALC_TAG.equalsIgnoreCase(args[0])) {
			logger.error("Invalid input parameter. Sample arguments : PriceBasket item1 item2 item3 ...");
			return;
		}

		Bill bill = null;
		PriceCalculator priceCalculator = new PriceCalculator();
		try {
			bill = priceCalculator.setOrders(Arrays.copyOfRange(args, 1, args.length));

		} catch (Exception e) {
			logger.error("Bill calculation Failure", e);
		}

		PrinterUtil printUtil = new PrinterUtil();
		try {
			printUtil.printResponse(bill);
		} catch (Exception e) {
			logger.error("Response print failure", e);
		}

	}

}
