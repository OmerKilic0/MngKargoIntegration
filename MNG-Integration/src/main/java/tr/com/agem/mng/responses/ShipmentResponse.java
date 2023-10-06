package tr.com.agem.mng.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//GetShipment için response
public class ShipmentResponse {

	private String shipmentServiceType;
	private String packagingType;
	private String paymentType;
	private String deliveryType;
	private String referenceId;
	private String shipmentId;
	private String shipmentSerialNumber;
	private String shipmentNumber;
	private String shipmentDateTime;
	private int pieceCount;
	private double totalKg;
	private double totalDesi;
	private double totalKgDesi;
	private double total;
	private double kdv;
	private double finalTotal;
	private int shipmentStatusCode;
	private int isMarketPlaceShipment;
	private int isMarketPlacePays;
	private String receivingBranch;
	private String shipperBranch;
	private String description;
	private String billOfLandingId;
	private int isCOD;
	private String codAmount;
	private String content;
	private String estimatedDeliveryDate;
	private int isDelivered;
	private String deliveryTo;
}