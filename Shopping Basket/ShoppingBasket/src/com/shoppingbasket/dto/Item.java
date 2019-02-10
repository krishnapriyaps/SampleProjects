package com.shoppingbasket.dto;

/**
 * 
 * DTO to hold item information
 *
 */
public class Item {

	private String name = null;

	private String measure = null;

	public Item(String name, String measure, double price) {
		super();
		this.name = name;
		this.measure = measure;
		this.price = price;
	}

	private double price = 0l;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isMatch = false;
		if (null != obj && this.getClass() == obj.getClass())
			isMatch = this.getName().equalsIgnoreCase(((Item) obj).getName());
		return isMatch;
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
}
