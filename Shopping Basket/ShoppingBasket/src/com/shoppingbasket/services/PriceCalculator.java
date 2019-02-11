package com.shoppingbasket.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.shoppingbasket.dto.Bill;
import com.shoppingbasket.dto.Item;
import com.shoppingbasket.dto.ItemPurchaseOrders;
import com.shoppingbasket.dto.Offer;
import com.shoppingbasket.util.ConfigLoader;
import com.shoppingbasket.util.PriceCalculatorUtils;

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
		Map<String, ItemPurchaseOrders> itemPurchaseOrderMap = new HashMap<String, ItemPurchaseOrders>();
		double subTotal = 0.0;

		for (String itemName : items) {
			itemName = itemName.toLowerCase();
			logger.debug("Item : " + itemName);
			if (ConfigLoader.getConfig().getItemPriceMap().containsKey(itemName)) {
				if (!itemPurchaseOrderMap.containsKey(itemName)) {
					ItemPurchaseOrders itemPurchaseOrders = new ItemPurchaseOrders();

					Item item = ConfigLoader.getConfig().getItemPriceMap().get(itemName);
					itemPurchaseOrders.setItem(item);
					itemPurchaseOrders.setOrderQuantity(1);
					itemPurchaseOrderMap.put(itemName, itemPurchaseOrders);

					subTotal = subTotal + item.getPrice();
					logger.debug("subTotal : " + subTotal);
				} else {
					itemPurchaseOrderMap.get(itemName)
							.setOrderQuantity(itemPurchaseOrderMap.get(itemName).getOrderQuantity() + 1);
					subTotal = subTotal + itemPurchaseOrderMap.get(itemName).getItem().getPrice();
				}
			} else {
				logger.debug("Contains Items not on sale.");
				throw new Exception("Contains Items not on sale.");
			}
		}

		Bill bill = new Bill();
		bill.setItemPurchaseOrderMap(itemPurchaseOrderMap);
		bill.setSubTotal(subTotal);

		this.applyDiscount(bill);

		return bill;
	}

	/**
	 * Method check whether offer is applicable for each item in the purchase order,
	 * Find out the number items the offer applies on and calculate and update bill
	 * object with applicable discounts
	 *
	 * @param Bill object
	 * 
	 */
	public void applyDiscount(Bill bill) {
		Map<String, ItemPurchaseOrders> purchaseOrderMap = bill.getItemPurchaseOrderMap();

		PriceCalculatorUtils priceCalcUtils = new PriceCalculatorUtils();

		for (Entry<String, ItemPurchaseOrders> entry : purchaseOrderMap.entrySet()) {

			if (ConfigLoader.getConfig().getItemOfferMap().containsKey(entry.getKey())) {

				ItemPurchaseOrders itemPurchaseOrders = entry.getValue();

				Offer offer = ConfigLoader.getConfig().getItemOfferMap().get(entry.getKey());

				logger.debug("Offer Valable for :" + itemPurchaseOrders.getItem().getName());

				boolean isOfferValid = priceCalcUtils.checkDateWithRange(offer.getOfferStartDate(),
						offer.getOfferEndDate());

				boolean offerQuantityCriteraFlag = priceCalcUtils.checkQunatityRequirment(offer, itemPurchaseOrders);

				if (offerQuantityCriteraFlag) {
					offer.setDiscountedItemQuantity(priceCalcUtils.maximumItemsOnOffer(offer, itemPurchaseOrders));
				}

				String discountedItemName = offer.getDiscountedItemName() != null ? offer.getDiscountedItemName()
						: itemPurchaseOrders.getItem().getName();

				if (isOfferValid && offerQuantityCriteraFlag
						&& purchaseOrderMap.containsKey(discountedItemName.toLowerCase())) {

					ItemPurchaseOrders discountedItemOrder = null;
					if (itemPurchaseOrders.getItem().getName().equalsIgnoreCase(discountedItemName)) {
						discountedItemOrder = itemPurchaseOrders;
					} else {
						discountedItemOrder = purchaseOrderMap.get(discountedItemName.toLowerCase());
					}

					int discountQuantity = priceCalcUtils.actualItemsOnOffer(offer, discountedItemOrder);

					discountedItemOrder.setDiscount(discountedItemOrder.getItem().getPrice() * discountQuantity
							* offer.getDiscountPercentage() / 100);

					discountedItemOrder.setDiscountPercentage(offer.getDiscountPercentage());
					logger.debug("Item: " + discountedItemOrder.getItem().getName() + ", Discount: "
							+ discountedItemOrder.getDiscount());

					bill.setDiscountAmount(bill.getDiscountAmount() + discountedItemOrder.getDiscount());
				}

			}

		}

		bill.setTotal(bill.getSubTotal() - bill.getDiscountAmount());

	}

}
