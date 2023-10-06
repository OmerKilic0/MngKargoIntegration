package tr.com.agem.mng.responses;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBarcode {

	private String referenceId;
	private String invoiceId;
	private String shipmentId;
	private List<Barcodes> barcodes;
}