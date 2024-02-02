package rx.ee.repository;

import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import rx.ee.jpaentity.Portfolio;

@Singleton
public class SingletonRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public Portfolio findPortfolioById(Long id) {
       return entityManager.find(Portfolio.class, id);
    }
}
