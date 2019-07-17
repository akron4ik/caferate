package repository.jpa;

import model.Cafe;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.CafeRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CafeRepositoryJpa implements CafeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Cafe save(Cafe cafe) {

        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Cafe get(int id) {
        return null;
    }

    @Override
    public List<Cafe> getAll(int userId) {

        return em.createNamedQuery(Cafe.ALL_SORTED, Cafe.class)
                .setParameter("user_id", userId)
                .getResultList();
    }
}
