package com.shoppingbasket.dto;

import java.util.Map;
/**
 * 
 * DTO to hold billing information
 *
 */
public class Bill {
	private double subTotal = 0.0;

	private double discountAmount = 0.0;

	private double total = 0.0;
	
	private Map<String, ItemPurchaseOrders> itemPurchaseOrderMap = null;

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Map<String, ItemPurchaseOrders> getItemPurchaseOrderMap() {
		return itemPurchaseOrderMap;
	}

	public void setItemPurchaseOrderMap(Map<String, ItemPurchaseOrders> itemPurchaseOrderMap) {
		this.itemPurchaseOrderMap = itemPurchaseOrderMap;
	}

}
