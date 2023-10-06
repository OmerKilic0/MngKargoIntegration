package tr.com.agem.mng.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseListResult<T> {
	
	private int statusCode;
	private String statusMessage;
	private List<T> result;
	private ErrorsInsideGeneralResponse[] errors;
}