package meal.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class IdGenerator {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Integer generateUniqueId() {
        Integer newId;
        do {
            newId = generateRandomId();
        } while (!isUnique(newId) || !isValid(newId));
        return newId;
    }

    private Integer generateRandomId() {
        return 100 + (int) (Math.random() * (999 - 100 + 1));
    }

    private boolean isUnique(Integer id) {
        Integer count = entityManager
                .createQuery("SELECT COUNT(u) FROM User u WHERE u.id = :id", Integer.class)
                .setParameter("id", id)
                .getSingleResult();
        return count == 0;
    }

    private boolean isValid(Integer id) {
        return id >= 100 && id <= 999;
    }
}