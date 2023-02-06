package rx.graborder.ui.jsf;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rx.graborder.jpaentity.OutPatientVisitOrder;
import rx.graborder.service.EscortService;
import rx.graborder.service.PatientService;
import rx.graborder.service.WithEntityManager;

@Named
@ViewScoped
public class RegisterOrderComponent implements Serializable {
	private String hospitalName;
	private String visitOrderItem;
	@Inject
	@WithEntityManager
	private PatientService patientService;
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getVisitOrderItem() {
		return visitOrderItem;
	}
	public void setVisitOrderItem(String visitOrderItem) {
		this.visitOrderItem = visitOrderItem;
	}
	public void register() {
		OutPatientVisitOrder newOrder = new OutPatientVisitOrder();
		newOrder.setHospitalName(hospitalName);
		newOrder.setOutPatientVisitItem(visitOrderItem);
		System.out.println("register::::::::::::::::::::::::" + hospitalName + " : " + visitOrderItem);
		patientService.registerOrder(newOrder);
	}

}
