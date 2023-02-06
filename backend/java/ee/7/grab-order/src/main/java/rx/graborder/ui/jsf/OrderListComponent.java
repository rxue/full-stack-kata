package rx.graborder.ui.jsf;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rx.graborder.jpaentity.OutPatientVisitOrder;
import rx.graborder.service.EscortService;
import rx.graborder.service.WithEntityManager;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;

@Named
@ViewScoped
public class OrderListComponent implements Serializable {
	@Inject
	@WithEntityManager
	private EscortService escortService;
	private List<OrderViewDTO> orders;
	
	public List<OrderViewDTO> getOrders() {
		List<OutPatientVisitOrder> rawOrders = escortService.getOrders();
		orders = rawOrders.stream()
				.map(OrderViewDTO::new)
				.collect(toList());
		return orders;
	}


	public void grab() {
		List<OrderViewDTO> tried = orders.stream().filter(OrderViewDTO::isTriedToGrab)
				.collect(toList());
		for (OrderViewDTO o : tried) {
			escortService.grabOrder(o.get());
		}
	}
}
