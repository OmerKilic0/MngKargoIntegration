package tr.com.agem.mng.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//GetOrder için response
public class OrderResponse {

	private String orderType;
	private String shipmentServiceType;
	private String packagingType;
	private int isTransformedToShipment; 
	private String paymentType;
	private String deliveryType;
	private String orderDate;
	private int isVerifiedOnMarketPlace;
	private String shipmentId;
	private String referenceId;
	private String barcode;
	private String billOfLandingId;
	private int isCOD;
	private int codAmount;
	private String content;
	private String pudoId;
	private int smsPreference1;
	private int smsPreference2;
	private int smsPreference3;
	private String description;
	private String marketPlaceShortCode;
	private String marketPlaceSaleCode;
}