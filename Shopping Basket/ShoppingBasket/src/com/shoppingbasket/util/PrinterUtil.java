package com.shoppingbasket.util;

import org.apache.log4j.Logger;

import com.shoppingbasket.dto.Bill;
import com.shoppingbasket.services.BillWithDiscountFormatter;
import com.shoppingbasket.services.BillWithoutDiscountFormatter;
import com.shoppingbasket.services.Formatter;

/**
 * Class to print response for user
 *
 */
public class PrinterUtil {
	private final static Logger logger = Logger.getLogger(PrinterUtil.class);

	/**
	 * Method that formats and print response
	 * 
	 * @param Bill object
	 * 
	 */
	public void printResponse(Bill bill) throws Exception {
		if (null != bill) {
			Formatter formatter = null;
			if (bill.getDiscountAmount() > 0) {
				formatter = new BillWithDiscountFormatter();
			}else {
				formatter = new BillWithoutDiscountFormatter();
			}
			
			String reciept = formatter.formatBill(bill);
			System.out.println(reciept);
		}
		else {
			logger.error("Bill calculation failure");
			throw new Exception("Bill calculation failure");
		}
		
	}
	
}
