package tr.com.agem.mng.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipientRequest {

	private String customerId;
	private String refCustomerId;
	private int cityCode;
	private String cityName;
	private int districtCode;
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