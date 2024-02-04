package rx.ee.bean;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.ApplicationScoped;
import rx.ee.jpaentity.Counter;

@ApplicationScoped
public class CDICounter {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void increment() {
        long threadId = Thread.currentThread().getId();
        System.out.println("Thread " + threadId + " before execute transaction");
        entityManager.createQuery("update Counter c set c.count = c.count + :increment")
                .setParameter("increment", 1L)
                .executeUpdate();
        System.out.println("Thread " + threadId + " after execute transaction");
    }
    public Counter get() {
        return entityManager.createQuery("select c from Counter c", Counter.class)
                .getResultList()
                .get(0);
    }
}