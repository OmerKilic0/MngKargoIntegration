package tr.com.agem.mng.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MngToken {
	
	private String jwt;
	private String refreshToken;
	private String jwtExpireDate;
	private String refreshTokenExpireDate;
}