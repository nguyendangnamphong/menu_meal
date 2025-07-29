package meal.app;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User createUser(User user) {
        persist(user);
        return user;
    }

    public User findById(Integer id) {
        return find("id", id).firstResult();
    }

    public void updateUser(User user) {
        update("name = ?1, role = ?2, disliked_dishes = ?3 where id = ?4",
                user.getName(), user.getRole(), user.getDislikedDishes(), user.getId());
    }

    public void deleteUser(Long id) {
        delete("id", id);
    }
}