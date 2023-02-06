package rx.graborder.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import rx.graborder.jpaentity.OutPatientVisitOrder;
@WithEntityManager
public class EscortServiceImpl implements EscortService, Serializable {
	@Inject
	private EntityManager em;
	@Transactional
	@Override
	public List<OutPatientVisitOrder> getOrders() {
		List<OutPatientVisitOrder> orders = em.createQuery("SELECT e FROM OutPatientVisitOrder e",
				OutPatientVisitOrder.class)
				.getResultList();
		for (OutPatientVisitOrder o : orders) {
			System.out.println("in EscortServiceImpl the order is in Managed state " + em.contains(o) + " id is " + o.getId());
		}
		OutPatientVisitOrder firstOrder = em.find(OutPatientVisitOrder.class, 2);
		System.out.println("in EscortServiceImpl found single order has single id " + firstOrder.getId() + " and it is in Managed state " + em.contains(firstOrder));
		return orders;
	}
	@Transactional
	@Override
	public boolean grabOrder(OutPatientVisitOrder order) {
		System.out.println("::DEBUG::grabbed order hash is " + order.hashCode());
		OutPatientVisitOrder attachedOrder = em.merge(order);
		System.out.println("before calling merge order is in EntityManager " + em.contains(order) + ", after merge order is in EntityManager " + em.contains(attachedOrder));
		em.remove(attachedOrder);
		return true;
	}

}
