package com.shoppingbasket.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.shoppingbasket.dto.Bill;
import com.shoppingbasket.dto.Item;
import com.shoppingbasket.dto.ItemPurchaseOrders;
import com.shoppingbasket.dto.Offer;
import com.shoppingbasket.util.ConfigLoader;

/**
 * 
 * Class to calculate price and discount
 *
 */
public class PriceCalculator {

	final static Logger logger = Logger.getLogger(PriceCalculator.class);

	/**
	 * 
	 * Method receives a list of items and calculates subtotal and total after
	 * applying discounts
	 * 
	 * @param list of items in the purchase order
	 * @return Bill object after caculating subtotal and total after applying
	 *         discounts
	 */
	public Bill setOrders(String[] items) throws Exception {
		Map<String, ItemPurchaseOrders> itemList = new HashMap<String, ItemPurchaseOrders>();
		double subTotal = 0.0;

		for (String itemName : items) {
			itemName = itemName.toLowerCase();
			logger.debug("Item : " + itemName);
			if (ConfigLoader.getConfig().getItemPrices().containsKey(itemName)) {
				if (!itemList.containsKey(itemName)) {
					ItemPurchaseOrders itemPurchaseOrders = new ItemPurchaseOrders();

					Item item = ConfigLoader.getConfig().getItemPrices().get(itemName);
					itemPurchaseOrders.setItem(item);
					itemPurchaseOrders.setOrderQuantity(1);
					itemList.put(itemName, itemPurchaseOrders);

					subTotal = subTotal + item.getPrice();
					logger.debug("subTotal : " + subTotal);
				} else {
					itemList.get(itemName).setOrderQuantity(itemList.get(itemName).getOrderQuantity() + 1);
					subTotal = subTotal + itemList.get(itemName).getItem().getPrice();
				}
			} else {
				logger.debug("Contains Items not on sale.");
				throw new Exception("Contains Items not on sale.");
			}
		}

		Bill bill = new Bill();
		bill.setItemList(itemList);
		bill.setSubTotal(subTotal);

		this.applyDiscount(bill);

		return bill;
	}

	/**
	 * Method to calculate and update bill object with applicable discounts
	 *
	 * @param Bill object
	 * 
	 */
	public void applyDiscount(Bill bill) {
		Map<String, ItemPurchaseOrders> itemList = bill.getItemList();
		for (Entry<String, ItemPurchaseOrders> entry : itemList.entrySet()) {

			if (ConfigLoader.getConfig().getOfferPolicys().containsKey(entry.getKey())) {

				ItemPurchaseOrders itemPurchaseOrders = entry.getValue();

				Offer offer = ConfigLoader.getConfig().getOfferPolicys().get(entry.getKey());

				logger.debug("Offer Valable for :" + itemPurchaseOrders.getItem().getName());

				// Check offer validity
				boolean isOfferValid = true;
				LocalDate presentDate = LocalDate.now();
				if ((null != offer.getOfferStartDate() && offer.getOfferStartDate().compareTo(presentDate) > 0)
						|| (null != offer.getOfferEndDate() && offer.getOfferEndDate().compareTo(presentDate) < 0)) {
					isOfferValid = false;
					logger.info("Minimum purchase criteria satified");
				}

				// Check whether purchase order has enough number of order for the offer to be
				// valid
				boolean offerQuantityCriteraFlag = false;

				if (offer.getOfferOnItemQuantity() == 0
						|| (offer.getOfferOnItemQuantity() <= itemPurchaseOrders.getOrderQuantity())) {

					offerQuantityCriteraFlag = true;

					// Find limit of discounted items the offer is applicable on
					if (offer.getOfferOnItemQuantity() > 0)
						offer.setDiscountedItemQuantity(
								itemPurchaseOrders.getOrderQuantity() / offer.getOfferOnItemQuantity());

					logger.debug("Minimum purchase criteria satified");
				} else {
					logger.debug("Minimum purchase criteria does not match. Offer valid for minimum "
							+ offer.getOfferOnItemQuantity() + " purchase of " + itemPurchaseOrders.getItem());
				}

				// find discounted item name
				String discountedItemName = offer.getDiscountedItemName() != null ? offer.getDiscountedItemName()
						: itemPurchaseOrders.getItem().getName();
				discountedItemName = discountedItemName.toLowerCase();

				boolean hasLimitOnDiscountQuantity = offer.getDiscountedItemQuantity() > 0;
				if (hasLimitOnDiscountQuantity) {
					logger.debug("Offer details, Discount of " + offer.getDiscountPercentage() + "% on "
							+ discountedItemName + " upto " + offer.getDiscountedItemQuantity() + " purchases");
				} else {
					logger.debug("Offer details, Discount of " + offer.getDiscountPercentage() + "% on "
							+ discountedItemName);
				}

				// Calculates discount if offer is applicable and the the discounted item in
				// present in purchase order
				if (isOfferValid && offerQuantityCriteraFlag && itemList.containsKey(discountedItemName)) {

					ItemPurchaseOrders discountedItemOrder = null;
					if (itemPurchaseOrders.getItem().getName().equalsIgnoreCase(discountedItemName)) {
						discountedItemOrder = itemPurchaseOrders;
					} else {
						discountedItemOrder = itemList.get(discountedItemName);
					}

					int discountQuantity = 0;
					if (hasLimitOnDiscountQuantity
							&& discountedItemOrder.getOrderQuantity() > offer.getDiscountedItemQuantity())
						discountQuantity = offer.getDiscountedItemQuantity();
					else
						discountQuantity = discountedItemOrder.getOrderQuantity();

					// Set individual discount
					discountedItemOrder.setDiscount(discountedItemOrder.getItem().getPrice() * discountQuantity
							* offer.getDiscountPercentage() / 100);
					
					discountedItemOrder.setDiscountPercentage(offer.getDiscountPercentage());
					logger.debug("Item: " + discountedItemOrder.getItem().getName() + ", Discount: "
							+ discountedItemOrder.getDiscount());
					
					// Update bill object
					bill.setDiscountAmount(bill.getDiscountAmount() + discountedItemOrder.getDiscount());
				}

			}

		}

		bill.setTotal(bill.getSubTotal() - bill.getDiscountAmount());

	}

}
