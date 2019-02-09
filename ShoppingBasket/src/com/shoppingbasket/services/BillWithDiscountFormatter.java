package com.shoppingbasket.services;

import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.shoppingbasket.dto.Bill;
import com.shoppingbasket.dto.ItemPurchaseOrders;
import com.shoppingbasket.util.Constants;

/**
 * 
 * Class to generate response for Bill with discount. Class extends Formatter
 * 
 * @see Formatter
 *
 */
public class BillWithDiscountFormatter extends Formatter {
	
	private final static Logger logger = Logger.getLogger(BillWithDiscountFormatter.class);

	/**
	 * Generate formated response for a Bill which have discount applied at least
	 * one item.
	 * 
	 * @param Bill
	 * @return formatted response
	 */
	@Override
	public String formatBill(Bill bill) {

		String individualDiscounts = this.formatIndividualDiscounts(bill);

		StringBuilder sb = new StringBuilder(Constants.BILL_WITH_DISCOUNT_TEMPLETE);
		super.replaceString(sb, Constants.INDIVIDUAL_DISCOUNT_TAG, individualDiscounts);
		super.replaceString(sb, Constants.SUBTOTAL_TAG, super.formatPrice(bill.getSubTotal()));
		super.replaceString(sb, Constants.TOTAL_TAG, super.formatPrice(bill.getTotal()));
		logger.debug("Formatted response: "+sb.toString());
		return sb.toString();
	}

	/**
	 * 
	 * Generate formatted response for each item with discount
	 * 
	 * @parm Bill
	 * @return formatted string
	 */
	private String formatIndividualDiscounts(Bill bill) {

		StringBuilder sub = new StringBuilder();

		for (Entry<String, ItemPurchaseOrders> entry : bill.getItemList().entrySet()) {
			if (entry.getValue().getDiscount() > 0) {
				sub.append(Constants.DISCOUNT_INDIVIDUAL_TEMPLETE);
				super.replaceString(sub, Constants.ITEM_TAG, entry.getValue().getItem().getName());
				super.replaceString(sub, Constants.PERCENTAGE_TAG,
						Integer.toString(entry.getValue().getDiscountPercentage()));
				super.replaceString(sub, Constants.DICOUNT_TAG, super.formatPrice(entry.getValue().getDiscount()));

			}
		}

		return sub.toString();

	}

}
