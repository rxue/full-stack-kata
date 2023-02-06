package rx.graborder.service;

import java.util.List;

import rx.graborder.jpaentity.OutPatientVisitOrder;

public interface EscortService {
	List<OutPatientVisitOrder> getOrders();
	/**
	 * Given an order, try to grab it
	 * 
	 * @param order
	 * @return
	 */
	boolean grabOrder(OutPatientVisitOrder order);	

}
