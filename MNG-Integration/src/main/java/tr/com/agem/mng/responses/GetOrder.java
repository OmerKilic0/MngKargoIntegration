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
public class GetOrder {
	
	private OrderResponse order;
	private List<OrderPieceListResponse> orderPieceList;
	private ShipperResponse shipper;
	private RecipientResponse recipient;
}