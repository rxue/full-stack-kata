package rx.graborder.ui.jsf;

import java.io.Serializable;

import rx.graborder.jpaentity.OutPatientVisitOrder;

public class OrderViewDTO implements Serializable {
	private final OutPatientVisitOrder order;
	private boolean triedToGrab;
	public OrderViewDTO(OutPatientVisitOrder order) {
		this.order = order;
	}
	public String getHospitalName() {
		return order.getHospitalName();
	}
	public String getOutPatientVisitItem() {
		return order.getOutPatientVisitItem();
	}
	public boolean isTriedToGrab() {
		return triedToGrab;
	}
	public void setTriedToGrab(boolean triedToGrab) {
		this.triedToGrab = triedToGrab;
	}
	public OutPatientVisitOrder get() {
		return order;
	}
	

}
