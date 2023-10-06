package tr.com.agem.mng.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPieceListRequest {

	private String barcode;
	private int desi;
	private int kg;
	private String content;
}