package tr.com.agem.mng.requests;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBarcodeRequest {

	private String referenceId;
	private String billOfLandingId;
	private int isCOD;
	private int codAmount;
	private int printReferenceBarcodeOnError;
	private String message;
	private String additionalContent1;
	private String additionalContent2;
	private String additionalContent3;
	private String additionalContent4;
	private List<OrderPieceListRequest> orderPieceList;
}