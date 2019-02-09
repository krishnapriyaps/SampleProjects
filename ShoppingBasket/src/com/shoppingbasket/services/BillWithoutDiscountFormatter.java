package com.shoppingbasket.services;

import org.apache.log4j.Logger;

import com.shoppingbasket.dto.Bill;
import com.shoppingbasket.util.Constants;

/**
 * 
 * Class to generate formatted response for bill without any discount Class
 * extends Formatter
 * 
 * @see Formatter
 *
 */
public class BillWithoutDiscountFormatter extends Formatter {

	private final static Logger logger = Logger.getLogger(BillWithoutDiscountFormatter.class);
	
	/**
	 * Generate formated response for a Bill which does not have discount
	 * 
	 * @param Bill
	 * @return formatted response
	 */
	@Override
	public String formatBill(Bill bill) {

		StringBuilder sb = new StringBuilder(Constants.BILL_WITHOUT_DISCOUNT_TEMPLETE);
		super.replaceString(sb, Constants.SUBTOTAL_TAG, super.formatPrice(bill.getSubTotal()));
		super.replaceString(sb, Constants.TOTAL_TAG, super.formatPrice(bill.getTotal()));
		logger.debug("Formatted response: "+sb.toString());
		return sb.toString();
	}

}
