package com.shoppingbasket.dto;

import java.util.Set;

/**
 * 
 * DTO to hold Offers applied on Items
 *
 */
public class ItemOffers {

	private String name = null;

	private Set<Offer> offersApplied = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Offer> getOffersApplied() {
		return offersApplied;
	}

	public void setOffersApplied(Set<Offer> offersApplied) {
		this.offersApplied = offersApplied;
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
