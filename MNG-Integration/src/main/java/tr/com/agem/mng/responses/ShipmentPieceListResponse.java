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
public class ShipmentPieceListResponse {

	private int numberOfPieces;
	private int kgDesi;
	private String barcode;
	private int desi;
	private int kg;
	private String content;
}
