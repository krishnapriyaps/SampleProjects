package com.shoppingbasket.util;

import java.time.LocalDate;

import org.apache.log4j.Logger;

import com.shoppingbasket.dto.ItemPurchaseOrders;
import com.shoppingbasket.dto.Offer;

/**
 * Class which assists PriceCalculator
 *
 */
public class PriceCalculatorUtils {
	final static Logger logger = Logger.getLogger(PriceCalculatorUtils.class);

	/**
	 *  Method to check preset date is within a certain date range
	 * 
	 *  @param Start date 
	 *  @param End date
	 *  
	 *  @return true if date is within the range and false if not within the range
	 */
	public boolean checkDateWithRange(LocalDate startDate, LocalDate endDate) {
		
		boolean isOfferValid = true;
		
		LocalDate presentDate = LocalDate.now();
		if ((null != startDate && startDate.compareTo(presentDate) > 0)
				|| (null != endDate && endDate.compareTo(presentDate) < 0)) {
			isOfferValid = false;
			logger.debug("Offer not within valid date range");
		}
		
		return isOfferValid;
	}
	/**
	 * Method check whether purchase order of item meets  quantity requirement for offer
	 * 
	 * @param Offer object
	 * @param ItemPurchaseorder
	 * 
	 * @return Return boolean true if offer is valid
	 * 		Return boolean false if offer is not valid
	 */
	public boolean checkQunatityRequirment(Offer offer, ItemPurchaseOrders itemPurchaseOrders) {
		
		boolean offerQuantity = false;
		if (offer.getOfferOnItemQuantity() == 0
				|| (offer.getOfferOnItemQuantity() <= itemPurchaseOrders.getOrderQuantity())) {

			offerQuantity = true;
			
			logger.debug("Minimum purchase criteria satified");
		} else {

			logger.debug("Minimum purchase criteria does not match. Offer valid for minimum "
					+ offer.getOfferOnItemQuantity() + " purchase of " + itemPurchaseOrders.getItem());
		}
		return offerQuantity;
	}
	
	/**
	 * Method to find the limit on purchase order
	 * 
	 * @param Offer object
	 * @param ItemPurchaseorder
	 * 
	 * @return Positive Integer greater than zero indicate the limit on number of items discount is applicable on. 
	 * 		Return value zero indicate offer applies to all items
	 */
	public int findQuantityLimit(Offer offer, ItemPurchaseOrders itemPurchaseOrders, String discountedItemName) {
		
		int offerQuantity = 0;

		// Find limit of discounted items the offer is applicable on
		if (offer.getOfferOnItemQuantity() > 0) {
			logger.debug("Offer details, Discount of " + offer.getDiscountPercentage() + "% on "
					+ discountedItemName + " upto " + offer.getDiscountedItemQuantity() + " purchases");
			offerQuantity = itemPurchaseOrders.getOrderQuantity() / offer.getOfferOnItemQuantity();
		}else {
			logger.debug("Offer details, Discount of " + offer.getDiscountPercentage() + "% on "
					+ discountedItemName);
		}

		return offerQuantity;
	}
	
	/**
	 * Method find out the maximum of item that offer applies to.
	 * 
	 * @param Offer object
	 * @param ItemPurchaseorder
	 * 
	 * @return returns maximum number of item that discount can be applied on
	 */
	public int maximumItemsOnOffer(Offer offer, ItemPurchaseOrders itemPurchaseOrders) {
		
		int offerQuantity = 0;

		// Find limit of discounted items the offer is applicable on
		if (offer.getOfferOnItemQuantity() > 0)
			offerQuantity = itemPurchaseOrders.getOrderQuantity() / offer.getOfferOnItemQuantity();

		logger.debug("Minimum purchase criteria satified");
		
		return offerQuantity;
	}
	
	
	/**
	 * Method find out the Actual number of item that offer applies to.
	 * 
	 * @param Offer object
	 * @param ItemPurchaseorder
	 * 
	 * @return returns actuam number of item that discount can be applied on
	 */
	public int actualItemsOnOffer(Offer offer, ItemPurchaseOrders itemPurchaseOrders) {
		
		int discountQuantity = 0;
		if ( offer.getDiscountedItemQuantity() > 0
				&& itemPurchaseOrders.getOrderQuantity() > offer.getDiscountedItemQuantity())
			discountQuantity = offer.getDiscountedItemQuantity();
		else
			discountQuantity = itemPurchaseOrders.getOrderQuantity();
		
		return discountQuantity;
	}
}
