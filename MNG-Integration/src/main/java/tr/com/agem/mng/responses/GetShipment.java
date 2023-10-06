package tr.com.agem.mng.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetShipment {

	private ShipmentResponse shipment;
	private List<OrderPieceListResponse> shipmentPieceList;
	private ShipperResponse shipper;
	private RecipientResponse recipient;
}