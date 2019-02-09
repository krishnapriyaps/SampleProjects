package com.shoppingbasket.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shoppingbasket.dto.Item;

/**
 * Class to load item - price information from JSON Data.
 * Class implements ItemPriceLoader
 * @see ItemPriceLoader
 */

public class JSONItemPriceLoader implements ItemPriceLoader<String> {
	
	/**
	 * Method to load item - price information from JSON string data
	 * 
	 * @param String with source information
	 * @return Map<String,Item> with item name as key and Item information as object
	 */
	@Override
	public Map<String,Item> loadItem(String sourceInfo) {

		ArrayList<Item> items = new Gson().fromJson(sourceInfo, new TypeToken<List<Item>>() {
		}.getType());

		Map<String,Item> itemMap = new HashMap<String, Item>();
		for(Item item: items) {
			itemMap.put(item.getName().toLowerCase(), item);
		}
		
		return itemMap;
	}

}
