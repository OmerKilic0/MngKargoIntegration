package tr.com.agem.mng.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Config {

	private String token;
	private String xApiVersion;
	private String createOrder;
	private String cancelOrder;
	private String getOrder;
	private String getShipment;
	private String getShipmentByShipmentId;
	private String getShipmentByDate;
	private String getDeliveredShipment;
	private String trackShipmentByReferenceId;
	private String trackShipmentByShipmentId;
	private String createBarcode;
	private String clientId;
	private String clientSecret;
	
	public Config() {
		super();
		Properties properties = loadProperties();
		propertiesToConfig(properties);
	}
	
	private void propertiesToConfig(Properties properties) {
		setToken(properties.getProperty("mng.token"));
		setXApiVersion(properties.getProperty("mng.x-api-version"));
		setCreateOrder(properties.getProperty("mng.create-order"));
		setCancelOrder(properties.getProperty("mng.cancel-order"));
		setGetOrder(properties.getProperty("mng.get-order"));
		setGetShipment(properties.getProperty("mng.get-shipment"));
		setGetShipmentByShipmentId(properties.getProperty("mng.get-shipment-by-shipment-id"));
		setGetShipmentByDate(properties.getProperty("mng.get-shipment-by-date"));
		setGetDeliveredShipment(properties.getProperty("mng.get-delivered-shipment"));
		setTrackShipmentByReferenceId(properties.getProperty("mng.track-shipment-by-reference-id"));
		setTrackShipmentByShipmentId(properties.getProperty("mng.track-shipment-by-shipment-id"));
		setCreateBarcode(properties.getProperty("mng.create-barcode"));
		setClientId(properties.getProperty("mng.client-id"));
		setClientSecret(properties.getProperty("mng.client-secret"));
	}
	
	private Properties loadProperties() {
		Properties prop = new Properties();
		loadProperties(prop, null);
		
		String profiles = System.getProperty("spring.profiles.active");
		if (profiles != null && !profiles.isEmpty()) {
			String[] profilesArr = profiles.split(",");
			for (int i = 0; i < profilesArr.length; i++) {
				String profile = profilesArr[i];
				loadProperties(prop, profile);
			}
		}
		return prop;
	}
	
	private void loadProperties(Properties prop, String profile) {
		try {
			String fileName = "mng";
			if (profile != null && !profile.isEmpty()) {
				fileName += "-" + profile;
			}
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName + ".properties");
			if (inputStream != null) {
				prop.load(inputStream);
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}