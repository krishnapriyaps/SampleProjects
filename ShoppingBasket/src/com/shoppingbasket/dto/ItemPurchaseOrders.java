package com.shoppingbasket.dto;

/**
 * 
 * DTO to hold Purchase info of Item
 *
 */
public class ItemPurchaseOrders {
	private Item item = null;

	private int orderQuantity = 0;
	
	private double discount = 0.0;

	private int discountPercentage = 0;
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
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
			isMatch = this.getItem().getName().equalsIgnoreCase(((ItemPurchaseOrders) obj).getItem().getName());
		return isMatch;
	}

	@Override
	public int hashCode() {
		return this.getItem().getName().hashCode();
	}

}
