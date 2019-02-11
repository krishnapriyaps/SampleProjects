package com.shoppingbasket.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shoppingbasket.dto.Offer;

/**
 * Class to load item - offers information from JSON string
 * 
 */
public class JSONOfferPolicyLoader implements OfferPolicyLoader<String> {

	/**
	 * Method to load item - offers information from JSON String
	 * 
	 * @param Object with source information
	 * @return Map<String,Offer> with item name as key and Offer information as
	 *         object
	 */
	@Override
	public Map<String, Offer> loadItem(String sourceInfo) {
		ArrayList<Offer> offerPolicys = new Gson().fromJson(sourceInfo, new TypeToken<List<Offer>>() {
		}.getType());

		Map<String, Offer> offerItemMap = new HashMap<String, Offer>();
		for (Offer offerPolicy : offerPolicys) {
			offerItemMap.put(offerPolicy.getOfferOnItemName().toLowerCase(), offerPolicy);
		}

		return offerItemMap;
	}

}
