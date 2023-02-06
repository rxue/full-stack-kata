package rx.graborder.service;

import rx.graborder.jpaentity.OutPatientVisitOrder;

public interface PatientService {
	void registerOrder(OutPatientVisitOrder newOrder);
}
