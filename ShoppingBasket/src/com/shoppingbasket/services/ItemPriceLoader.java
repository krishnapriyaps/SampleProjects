package com.shoppingbasket.services;

import java.util.Map;

import com.shoppingbasket.dto.Item;

/**
 * Interface to load item - price information from source
 */
public interface ItemPriceLoader<T> {

	/**
	 * Method to load item - price information from source
	 * 
	 * @param Object with source information
	 * @return Map<String,Item> with item name as key and Item information as object
	 */
	public Map<String,Item> loadItem(T sourceInfo);
}
