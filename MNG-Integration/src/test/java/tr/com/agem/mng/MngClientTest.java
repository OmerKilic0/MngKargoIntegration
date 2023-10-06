package tr.com.agem.mng;

import org.junit.jupiter.api.Assertions;

import com.fasterxml.jackson.core.JsonProcessingException;

import tr.com.agem.mng.controller.MngClient;
import tr.com.agem.mng.requests.CreateBarcodeRequest;
import tr.com.agem.mng.requests.CreateOrderRequest;
import tr.com.agem.mng.requests.OrderPieceListRequest;
import tr.com.agem.mng.requests.OrderRequest;
import tr.com.agem.mng.requests.RecipientRequest;
import tr.com.agem.mng.responses.CreateBarcode;
import tr.com.agem.mng.responses.CreateOrder;
import tr.com.agem.mng.responses.GetOrder;
import tr.com.agem.mng.responses.GetShipment;
import tr.com.agem.mng.responses.MngToken;
import tr.com.agem.mng.responses.TrackShipment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MngClientTest {
	
	static {
		System.setProperty("profile", "live");
	}
	
	String customerNumber = "20474979";
	String password = "2047497911";
	String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyMDQ3NDk3OSIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6IlVzZXIiLCJqdGkiOiI4YWRkMDczMi0wMWEwLTQzOTItYTFlNC05MGVmZjAwNTk3ODQiLCJleHAiOjE2ODk2MDUwNjQsImlzcyI6Ind3dy5tbmdrYXJnby5jb20udHIiLCJhdWQiOiJpbnRlZ3JhdGlvbi1kZXZlbG9wZXJzIn0.vaTU6Ca7FME0HZkIUUCnJ3UpG9uYDbgTu90rzYqSeWI";
	String referenceId = "SIPARISNUMARANIZ1324";
	String barcode = referenceId;
	String shipmentId = "221629659534";
//	Successful
	@Test
	public void MngClient_getToken() {
		MngClient client = MngClient.getInstance();
		MngToken token = client.getToken(customerNumber, password);
		this.token = token.getJwt();
		System.out.println(this.token);
		Assertions.assertNotNull(token);
	}
//  Successful
	@Test
	public void MngClient_createOrder() throws JsonProcessingException {
		MngClient client = MngClient.getInstance();
		OrderRequest order = new OrderRequest(referenceId, barcode, "İrsaliye 1", 0, 0, 1, 3, "İçerik 1", 0, 0, 0, 1, 1, "Açıklama 1", "", "");
		List<OrderPieceListRequest> orderPieceList = new ArrayList<>();
		orderPieceList.add(new OrderPieceListRequest("KALEM_BARCODE1", 2, 2, "Parça açıklama 1"));
		orderPieceList.add(new OrderPieceListRequest("KALEM_BARCODE2", 2, 2, "Parça açıklama 2"));
		RecipientRequest recipient = new RecipientRequest("", "", 0, "Ankara", 0, "Çankaya", "ALICI TEXT ADRESİ", "", "A@A.COM.TR", "", "", "ALICI AD SOYAD", "", "5551231212");
		CreateOrderRequest createOrderRequest = new CreateOrderRequest(order, orderPieceList, recipient);
		List<CreateOrder> createOrder = client.createOrder(token, createOrderRequest);
		Assertions.assertNotNull(createOrder);
	}
//	500 --> postman: "46.1.113.154 IP adresi için tek barkod yetkiniz yoktur."
	@Test
	public void MngClient_createBarcode() throws JsonProcessingException {
		MngClient client = MngClient.getInstance();
		List<OrderPieceListRequest> orderPieceList = new ArrayList<>();
		orderPieceList.add(new OrderPieceListRequest(referenceId, 2, 2, "kalem"));
		orderPieceList.add(new OrderPieceListRequest(referenceId, 2, 1, "defter"));
		CreateBarcodeRequest createBarcodeRequest = new CreateBarcodeRequest(referenceId, "İrsaliye 1", 0, 0, 0, "Mesaj 1", "", "", "", "", orderPieceList);
		List<CreateBarcode> createBarcode = client.createBarcode(token, createBarcodeRequest);
		Assertions.assertNotNull(createBarcode);
	}
//	Successful
	@Test
	public void MngClient_getOrder() {
		MngClient client = MngClient.getInstance();
		List<GetOrder> getOrder = client.getOrder(token, referenceId);
		Assertions.assertNotNull(getOrder);
	}
//	Successful
	@Test
	public void MngClient_getShipment() {
		MngClient client = MngClient.getInstance();
		List<GetShipment> getShipment = client.getShipment(token, referenceId);
		Assertions.assertNotNull(getShipment);
	}
//	Successful
	@Test
	public void MngClient_getShipmentByShipmentId() {
		MngClient client = MngClient.getInstance();
		List<GetShipment> getShipment = client.getShipmentByShipmentId(token, shipmentId);
		Assertions.assertNotNull(getShipment);
	}
	
	@Test
	public void MngClient_getShipmentByDate() {
		MngClient client = MngClient.getInstance();
		List<GetShipment> getShipment = client.getShipmentByDate(token, "10.10.2020");
		Assertions.assertNotNull(getShipment);
	}
	
	@Test
	public void MngClient_getDeliveredShipment() {
		MngClient client = MngClient.getInstance();
		List<GetShipment> getShipment = client.getDeliveredShipment(token, "10.10.2020");
		Assertions.assertNotNull(getShipment);
	}
//	Successful
	@Test
	public void MngClient_trackShipmentByReferenceId() {
		MngClient client = MngClient.getInstance();
		List<TrackShipment> trackShipment = client.trackShipmentByReferenceId(token, referenceId);
		Assertions.assertNotNull(trackShipment);
	}
//	Successful
	@Test
	public void MngClient_trackShipmentByShipmentId() {
		MngClient client = MngClient.getInstance();
		List<TrackShipment> trackShipment = client.trackShipmentByShipmentId(token, shipmentId);
		Assertions.assertNotNull(trackShipment);
	}
////	put(null)
//	@Test
//	public void MngClient_cancelOrder() throws JsonProcessingException {
//		MngClient client = MngClient.getInstance();
//		client.cancelOrder(token, referenceId);
//	}
}