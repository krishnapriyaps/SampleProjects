package com.shoppingbasket.dto;

import java.time.LocalDate;

/**
 * 
 * DTO to hold Offer details
 *
 */
public class Offer {

	public Offer(String offerOnItemName, int offerOnItemQuantity, int discountPercentage) {
		this(offerOnItemName, offerOnItemQuantity, null, null, null, 0, discountPercentage);
	}

	public Offer(String offerOnItemName, int offerOnItemQuantity, LocalDate offerStartDate,
			LocalDate offerEndDate, String discountedItemName, int discountedItemQuantity, int discountPercentage) {
		this.offerOnItemName = offerOnItemName;
		this.offerOnItemQuantity = offerOnItemQuantity;
		this.offerStartDate = offerStartDate;
		this.offerEndDate = offerEndDate;
		this.discountedItemName = discountedItemName;
		this.discountedItemQuantity = discountedItemQuantity;
		this.discountPercentage = discountPercentage;
	}

	private String offerOnItemName = null;

	private int offerOnItemQuantity = 0;

	private LocalDate offerStartDate = null;

	private LocalDate offerEndDate = null;

	private String discountedItemName = null;

	private int discountedItemQuantity = 0;

	private int discountPercentage = 0;

	public String getOfferOnItemName() {
		return offerOnItemName;
	}

	public void setOfferOnItemName(String offerOnItemName) {
		this.offerOnItemName = offerOnItemName;
	}

	public int getOfferOnItemQuantity() {
		return offerOnItemQuantity;
	}

	public void setOfferOnItemQuantity(int offerOnItemQuantity) {
		this.offerOnItemQuantity = offerOnItemQuantity;
	}

	public LocalDate getOfferStartDate() {
		return offerStartDate;
	}

	public void setOfferStartDate(LocalDate offerStartDate) {
		this.offerStartDate = offerStartDate;
	}

	public LocalDate getOfferEndDate() {
		return offerEndDate;
	}

	public void setOfferEndDate(LocalDate offerEndDate) {
		this.offerEndDate = offerEndDate;
	}

	public String getDiscountedItemName() {
		return discountedItemName;
	}

	public void setDiscountedItemName(String discountedItemName) {
		this.discountedItemName = discountedItemName;
	}

	public int getDiscountedItemQuantity() {
		return discountedItemQuantity;
	}

	public void setDiscountedItemQuantity(int discountedItemQuantity) {
		this.discountedItemQuantity = discountedItemQuantity;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isMatch = false;
		if (null != obj && this.getClass() == obj.getClass())
			isMatch = this.getOfferOnItemName().equalsIgnoreCase(((Offer) obj).getOfferOnItemName());
		return isMatch;
	}

	@Override
	public int hashCode() {
		return this.getOfferOnItemName().hashCode();
	}

}
