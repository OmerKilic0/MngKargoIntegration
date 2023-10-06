package tr.com.agem.mng.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackShipment {
	
	private String referenceId;
	private String shipmentId;
	private String eventSequence;
	private String eventStatus;
	private String eventDateTime;
	private String location;
	private String locationAddress;
	private String locationPhone;
	private String deliveryDateTime;
	private String deliveryTo;
	private String description;
	private String pieceTotal;
}