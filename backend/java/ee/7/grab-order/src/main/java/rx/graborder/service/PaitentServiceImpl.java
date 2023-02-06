package rx.graborder.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import rx.graborder.jpaentity.OutPatientVisitOrder;
import rx.graborder.repository.OutPatientVisitOrderRepository;
@WithEntityManager
public class PaitentServiceImpl implements PatientService, Serializable {
	@Inject
	private OutPatientVisitOrderRepository repo;
	@Transactional
	@Override
	public void registerOrder(OutPatientVisitOrder newOrder) {
		repo.create(newOrder);
	}

}
