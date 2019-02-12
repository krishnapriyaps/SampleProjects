package com.shoppingbasket.util;

/**
 * Class to hold all tags and templates required for formatting and reading data
 *
 */
public class Constants {

	public static final String POUND_CHAR = "£";

	public static final String PENCE_CHAR = "p";

	public static final String PRICE_CALC_TAG = "PriceBasket";

	public static final String JSON_TAG = "JSON";

	public static final String POUND_TAG = "<pound>";

	public static final String PENCE_TAG = "<pence>";

	public static final String SUBTOTAL_TAG = "<subtotal>";

	public static final String TOTAL_TAG = "<total>";

	public static final String INDIVIDUAL_DISCOUNT_TAG = "<individual_discounts>";

	public static final String ITEM_TAG = "<item>";

	public static final String PERCENTAGE_TAG = "<percentage>";

	public static final String DICOUNT_TAG = "<discount>";

	public static final String AMOUNT_TAG = "<amount>";

	public static final String BILL_WITHOUT_DISCOUNT_TEMPLETE = "Subtotal: <subtotal>\n"
			+ "(No offers available)\nTotal price: <total>";

	public static final String BILL_WITH_DISCOUNT_TEMPLETE = "Subtotal: <subtotal>\n<individual_discounts>Total: <total>";

	public static final String DISCOUNT_INDIVIDUAL_TEMPLETE = "<item> <percentage>% off: -­<discount>\n";

	public static final String PRICE_TEMPLETE = "<pound><amount><pence>";

}
