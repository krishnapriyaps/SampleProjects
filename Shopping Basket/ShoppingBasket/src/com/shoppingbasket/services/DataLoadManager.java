package com.shoppingbasket.services;

import org.apache.log4j.Logger;

import com.shoppingbasket.util.Constants;

/**
 * 
 * DataLoad Manager class is a singleton class which drives the mode of loading
 * item price list details and offer details
 *
 */
public class DataLoadManager {
	
	private final static Logger logger = Logger.getLogger(DataLoadManager.class);

	private static final DataLoadManager manager = new DataLoadManager();

	private DataLoadManager() {
	}

	public static DataLoadManager getInstance() {
		return manager;
	}

	/**
	 * Method determines the source to load price - item lists
	 * 
	 * @param String sourcetype indicator
	 * 
	 * @return Instance of ItemPriceLoader based on the sourceType
	 * 
	 */
	public static ItemPriceLoader getPriceLoader(String sourceType) throws Exception {
		ItemPriceLoader priceloader = null;
		if (sourceType.equalsIgnoreCase(Constants.JSON_TAG)) {
			priceloader = new JSONItemPriceLoader();
		} else {
			logger.error("Unimpremented source type detected :"+ sourceType);
			throw new Exception("Source Type not implemented");
		}
		return priceloader;
	}

	/**
	 * Method determines the source to load item - offer lists
	 * 
	 * @param String sourcetype indicator
	 * 
	 * @return Instance of OfferPolicyLoader based on the sourceType
	 * 
	 */
	public static OfferPolicyLoader getOfferPolicyLoader(String sourceType) throws Exception {
		OfferPolicyLoader offerPolicyLoader = null;
		if (sourceType.equalsIgnoreCase(Constants.JSON_TAG)) {
			offerPolicyLoader = new JSONOfferPolicyLoader();
		} else {
			logger.error("Unimpremented source type detected :"+ sourceType);
			throw new Exception("Source Type not implemented");
		}
		return offerPolicyLoader;
	}
}
