package tr.com.agem.mng.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//CreateBarcode için response
public class Barcodes {

	private int pieceNumber;
	private String value;
}