package com.shoppingbasket.services;

import java.util.Map;

import com.shoppingbasket.dto.Offer;

/**
 * Interface to load item - offers information from source specified
 * 
 */
public interface OfferPolicyLoader<T> {
	
	/**
	 * Method to load item - offers information from source
	 * 
	 * @param Object with source information
	 * @return Map<String,Offer> with item name as key and Offer information as object
	 */
	public Map<String,Offer> loadItem(T sourceInfo);
}
