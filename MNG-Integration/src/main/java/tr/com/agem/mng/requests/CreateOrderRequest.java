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
public class CreateOrderRequest {
	
	private OrderRequest order;
	private List<OrderPieceListRequest> orderPieceList;
	private RecipientRequest recipient;
}