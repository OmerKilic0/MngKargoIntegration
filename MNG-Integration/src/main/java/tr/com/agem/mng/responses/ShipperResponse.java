package tr.com.agem.mng.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//GetOrder-GetShipment için response
public class ShipperResponse {

	private int customerId;
	private String refCustomerId;
	private String city;
	private String district;
	private String cityName;
	private String districtName;
	private String address;
	private String businessPhoneNumber;
	private String email;
	private String taxOffice;
	private String taxNumber;
	private String fullName;
	private String homePhoneNumber;
	private String mobilePhoneNumber;
}