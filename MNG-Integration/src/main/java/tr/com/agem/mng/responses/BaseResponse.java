package tr.com.agem.mng.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
	
	private int statusCode;
	private String statusMessage;
	private T result;
	private ErrorsInsideGeneralResponse[] errors;
}