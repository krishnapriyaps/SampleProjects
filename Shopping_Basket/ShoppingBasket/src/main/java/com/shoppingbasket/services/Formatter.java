package com.shoppingbasket.services;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import com.shoppingbasket.dto.Bill;
import com.shoppingbasket.util.Constants;

/**
 * 
 * Abstract Class to generate response for Bill.
 * 
 */
public abstract class Formatter {
	final static Logger logger = Logger.getLogger(Formatter.class);

	/**
	 * Public method to format amount.
	 * 
	 * @param bill amount to be formatted
	 * @return formatted string
	 */
	public String formatPrice(double amount) {
		StringBuilder sb = new StringBuilder(Constants.PRICE_TEMPLETE);
		if (amount > 1) {
			this.deleteString(sb, Constants.PENCE_TAG);
			this.replaceString(sb, Constants.POUND_TAG, Constants.POUND_CHAR);
			this.replaceString(sb, Constants.AMOUNT_TAG, this.formatPound(amount));

		} else {
			this.deleteString(sb, Constants.POUND_TAG);
			this.replaceString(sb, Constants.PENCE_TAG, Constants.PENCE_CHAR);
			this.replaceString(sb, Constants.AMOUNT_TAG, this.formatPence(amount));

		}
		return sb.toString();
	}

	/**
	 * Private method to format bill double in pound
	 * 
	 * @param Bill amount
	 * @return formatted String
	 */
	private String formatPound(double value) {

		value = value * 100;
		value = Math.round(value);
		value = value / 100;
		DecimalFormat df = new DecimalFormat("#.00");
	    return df.format(value);
	}

	/**
	 * Private method to format bill double in pence
	 * 
	 * @param Bill amount
	 * @return formatted String
	 */
	private String formatPence(double value) {

		value = value * 100;
		value = Math.round(value);
		value = value / 100;

		return Integer.toString((int) (value * 100));
	}

	/**
	 * Public method to delete a String from StringBuilder
	 * 
	 * @param StringBuilder object
	 * @param String        to be deleted
	 * 
	 */
	public void deleteString(StringBuilder sb, String stringToDelete) {
		sb.delete(sb.indexOf(stringToDelete), sb.indexOf(stringToDelete) + stringToDelete.length());
	}

	/**
	 * Public method to replace a String with a new String in a StringBuilder
	 * 
	 * @param StringBuilder object
	 * @param String        to be deleted
	 * @param String        to be inserted
	 * 
	 */
	public void replaceString(StringBuilder sb, String oldString, String newString) {
		sb.replace(sb.indexOf(oldString), sb.indexOf(oldString) + oldString.length(), newString);
	}

	/**
	 * Public Method to format response formatBill Formatter String
	 */
	public abstract String formatBill(Bill bill);
}
