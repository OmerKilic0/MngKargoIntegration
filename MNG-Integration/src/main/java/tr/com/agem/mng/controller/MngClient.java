package tr.com.agem.mng.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import tr.com.agem.mng.requests.CreateBarcodeRequest;
import tr.com.agem.mng.requests.CreateOrderRequest;
import tr.com.agem.mng.responses.CreateOrder;
import tr.com.agem.mng.responses.CreateBarcode;
import tr.com.agem.mng.responses.GetOrder;
import tr.com.agem.mng.responses.GetShipment;
import tr.com.agem.mng.responses.MngToken;
import tr.com.agem.mng.responses.TrackShipment;

public class MngClient {
	
	private static MngClient instance = null;
	
	public static synchronized MngClient getInstance() {
		if (instance == null) {
			instance = new MngClient();
		}
		return instance;
	}

	private static final Logger logger = Logger.getLogger(MngClient.class);
	private OkHttpClient okhttp;
	private Config config;
	private ObjectMapper objectMapper;
	
	public MngClient() {
		this.config = new Config();
		OkHttpClient client = new OkHttpClient().newBuilder()
		.readTimeout(30, TimeUnit.SECONDS)
		.writeTimeout(30, TimeUnit.SECONDS)
		.connectTimeout(30,TimeUnit.SECONDS)
		.build();
		this.okhttp = client;
		
		this.objectMapper = new ObjectMapper();
		this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		this.objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	}
	
	public MngToken getToken(String customerNumber, String password) {
		String url = this.config.getToken();
		MngToken returnItem = null;
		
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), 
				"{"
						+ "\"customerNumber\": "+ customerNumber + ","
						+ "\"password\": " + password + ","
						+ "\"identityType\":1}");
		
		Builder builder = createBaseRequestBuilder(url);
		Request request = builder
		.post(requestBody)
		.build();
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				returnItem = objectMapper.readValue(body.string(), new TypeReference<MngToken>() {});
				return returnItem;
			}
		}
		catch(Exception e) {
			logger.error("Mng token alınırken beklenmedik hata oluştu!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
		return returnItem;
	}
	
	public List<CreateOrder> createOrder(String token, CreateOrderRequest createOrder) throws JsonProcessingException{
		String url = this.config.getCreateOrder();
		List<CreateOrder> returnItem = null;
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(createOrder);
		
    	RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
    	Builder builder = createBaseRequestBuilder(url, token);
		Request request = builder
		.post(requestBody)
		.build();
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				returnItem = objectMapper.readValue(body.bytes(), new TypeReference<List<CreateOrder>>() {});
				return returnItem;
			}
		}
		catch(Exception e) {
			logger.error("Sipariş oluşturulurken beklenmedik hata oluştu!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
		return returnItem;
	}
	
	public void cancelOrder(String token, String referenceId) {
		String baseUrl = this.config.getCancelOrder();
		String url = baseUrl + referenceId;
		
		Builder builder = createBaseRequestBuilder(url, token);
		Request request = builder
		.put(null)
		.build();
		
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				System.out.println("Sipariş iptal edildi.");
			}
		}
		catch(Exception e) {
			logger.error("Sipariş iptal edilemedi!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
	}
	
	public List<GetOrder> getOrder(String token, String referenceId) {
		String baseUrl = this.config.getGetOrder();
		String url = baseUrl + referenceId;
		List<GetOrder> returnItem = null;
		
		Builder builder = createBaseRequestBuilder(url, token);
		Request request = builder
		.get()
		.build();
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				returnItem = objectMapper.readValue(body.bytes(), new TypeReference<List<GetOrder>>() {});
				return returnItem;
			}
		}
		catch(Exception e) {
			logger.error("Sipariş bilgileri getirilirken beklenmedik hata oluştu!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
		return returnItem;
	}
	
	public List<GetShipment> getShipment(String token, String referenceId){
		String baseUrl = this.config.getGetShipment();
		String url = baseUrl + referenceId;
		List<GetShipment> returnItem = null;
		
		Builder builder = createBaseRequestBuilder(url, token);
		Request request = builder
		.get()
		.build();
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				returnItem = objectMapper.readValue(body.bytes(), new TypeReference<List<GetShipment>>() {});
				return returnItem;
			}
		}
		catch(Exception e) {
			logger.error("Gönderi bilgileri getirilirken hata oluştu!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
		return returnItem;
	}
	
	public List<GetShipment> getShipmentByShipmentId(String token, String shipmentId){
		String baseUrl = this.config.getGetShipmentByShipmentId();
		String url = baseUrl + shipmentId;
		List<GetShipment> returnItem = null;
		
		Builder builder = createBaseRequestBuilder(url, token);
		Request request = builder
		.get()
		.build();
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				returnItem = objectMapper.readValue(body.bytes(), new TypeReference<List<GetShipment>>() {});
				return returnItem;
			}
		}
		catch(Exception e) {
			logger.error("Gönderi bilgileri getirilirken hata oluştu!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
		return returnItem;
	}
	
	public List<GetShipment> getShipmentByDate(String token, String startDate) {
		String baseUrl = this.config.getGetShipmentByDate();
		String url = baseUrl + startDate;
		List<GetShipment> returnItem = null;
		
		Builder builder = createBaseRequestBuilder(url, token);
		Request request = builder
		.get()
		.build();
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				returnItem = objectMapper.readValue(body.bytes(), new TypeReference<List<GetShipment>>() {});
				return returnItem;
			}
		}
		catch(Exception e) {
			logger.error("Gönderi bilgileri getirilirken hata oluştu!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
		return returnItem;
	}
	
	public List<GetShipment> getDeliveredShipment(String token, String startDate) {
		String baseUrl = this.config.getGetShipmentByDate();
		String url = baseUrl + startDate;
		List<GetShipment> returnItem = null;
		
		Builder builder = createBaseRequestBuilder(url, token);
		Request request = builder
		.get()
		.build();
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				returnItem = objectMapper.readValue(body.bytes(), new TypeReference<List<GetShipment>>() {});
				return returnItem;
			}
		}
		catch(Exception e) {
			logger.error("Gönderi bilgileri getirilirken hata oluştu!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
		return returnItem;
	}
	
	public List<TrackShipment> trackShipmentByReferenceId(String token, String referenceId){
		String baseUrl = this.config.getTrackShipmentByReferenceId();
		String url = baseUrl + referenceId;
		List<TrackShipment> returnItem = null;
		
		Builder builder = createBaseRequestBuilder(url, token);
		Request request = builder
		.get()
		.build();
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				returnItem = objectMapper.readValue(body.bytes(), new TypeReference<List<TrackShipment>>() {});
				return returnItem;
			}
		}
		catch(Exception e) {
			logger.error("Gönderi hareketleri getirilirken hata oluştu!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
		return returnItem;
	}
	
	public List<TrackShipment> trackShipmentByShipmentId(String token, String shipmentId){
		String baseUrl = this.config.getTrackShipmentByShipmentId();
		String url = baseUrl + shipmentId;
		List<TrackShipment> returnItem = null;
		
		Builder builder = createBaseRequestBuilder(url, token);
		Request request = builder
		.get()
		.build();
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				returnItem = objectMapper.readValue(body.bytes(), new TypeReference<List<TrackShipment>>() {});
				return returnItem;
			}
		}
		catch(Exception e) {
			logger.error("Gönderi hareketleri getirilirken hata oluştu!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
		return returnItem;
	}
	
	public List<CreateBarcode> createBarcode(String token, CreateBarcodeRequest createBarcode) throws JsonProcessingException{
		String url = this.config.getCreateBarcode();
		List<CreateBarcode> returnItem = null;
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(createBarcode);
		
    	RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
    	Builder builder = createBaseRequestBuilder(url, token);
		Request request = builder
		.post(requestBody)
		.build();
		Response response = null;
		
		try {
			response = okhttp.newCall(request).execute();
			if(response.isSuccessful()) {
				ResponseBody body = response.body();
				returnItem = objectMapper.readValue(body.bytes(), new TypeReference<List<CreateBarcode>>() {});
				return returnItem;
			}
		}
		catch(Exception e) {
			logger.error("Barkod etiketi üretilirken beklenmedik hata oluştu!! Code: " + response.code() + ", Message: " + response.message(), e);
		}
		finally {
			if(response != null){
				response.close();
			}
		}
		return returnItem;
	}
	
	private Builder createBaseRequestBuilder(String url, String token) {
		Builder builder = new Request.Builder()
				.url(url)
				.addHeader("x-ibm-client-id", this.config.getClientId())
				.addHeader("x-ibm-client-secret", this.config.getClientSecret())
				.addHeader("x-api-version", this.config.getXApiVersion())
				.addHeader("content-type", "application/json")
				.addHeader("accept", "application/json");
		if (token != null) {
			builder.addHeader("Authorization", "Bearer " + token);
		}
		return builder;
	}
	
	private Builder createBaseRequestBuilder(String url) {
		return createBaseRequestBuilder(url, null);
	}
}