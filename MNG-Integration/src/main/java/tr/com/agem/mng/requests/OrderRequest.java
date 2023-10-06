package tr.com.agem.mng.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

	private String referenceId;
	private String barcode;
	private String billOfLandingId;
	private int isCOD;
	private int codAmount;
	private int shipmentServiceType;
	private int packagingType;
	private String content;
	private int smsPreference1;
	private int smsPreference2;
	private int smsPreference3;
	private int paymentType;
	private int deliveryType;
	private String description;
	private String marketPlaceShortCode;
	private String marketPlaceSaleCode;
}