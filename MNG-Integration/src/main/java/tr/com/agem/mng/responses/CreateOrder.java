package tr.com.agem.mng.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrder {
	
	private String orderInvoiceId;
	private String orderInvoiceDetailId;
	private String shipperBranchCode;
}