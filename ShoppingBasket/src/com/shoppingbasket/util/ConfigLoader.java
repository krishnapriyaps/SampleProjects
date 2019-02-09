package com.shoppingbasket.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.shoppingbasket.dto.Item;
import com.shoppingbasket.dto.Offer;
import com.shoppingbasket.services.DataLoadManager;

/**
 * Singleton class to load and hold configuration information from
 * configuration.properties file
 * 
 */
public final class ConfigLoader {
	
	private final static Logger logger = Logger.getLogger(ConfigLoader.class);

	private static final ConfigLoader config = new ConfigLoader();

	private ConfigLoader() {
	}

	private static Map<String, Item> itemPrices = null;

	private static Map<String, Offer> offerPolicys = null;

	public static ConfigLoader getConfig() {
		return config;
	}

	public Map<String, Item> getItemPrices() {
		return itemPrices;
	}

	public Map<String, Offer> getOfferPolicys() {
		return offerPolicys;
	}

	/*
	 * Static code to load properties form configuration.properties file and load
	 * itemlist and offerlist form corresponding sources as specified in
	 * configuration file.
	 */
	static {

		Properties prop = getProperties();

		// Read itemList and populate Items - details set
		try {
			itemPrices = DataLoadManager.getInstance().getPriceLoader(prop.getProperty("sourceDataType"))
					.loadItem(prop.getProperty("pricelist"));
			logger.debug("pricelist size :" + itemPrices.size());

		} catch (Exception e) {
			logger.error("Loading item prices failed", e);
		}

		// Read offerList and populate Items - offer set
		try {
			offerPolicys = DataLoadManager.getInstance().getOfferPolicyLoader(prop.getProperty("sourceDataType"))
					.loadItem(prop.getProperty("offerlist"));
			logger.debug("offerPolicys size :" + offerPolicys.size());
		} catch (Exception e) {
			logger.error("Loading item offers failed", e);
		}
	}

	/**
	 * Method to read data from property file 
	 * 
	 */
	private static Properties getProperties() {

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = ConfigLoader.class.getClassLoader().getResourceAsStream("configuration.properties");
			prop.load(input);
		} catch (IOException ex) {
			logger.error("Reading property file failed", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("Reading property file improperly clossed", e);
				}
			}
		}
		return prop;
	}
}
