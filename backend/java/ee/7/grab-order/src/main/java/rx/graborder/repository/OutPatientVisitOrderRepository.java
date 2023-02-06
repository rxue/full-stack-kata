package rx.graborder.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import rx.graborder.jpaentity.OutPatientVisitOrder;

public class OutPatientVisitOrderRepository {
	@Inject
	private EntityManager em;
	public void create(OutPatientVisitOrder order) {
		em.persist(order);
	}

}
