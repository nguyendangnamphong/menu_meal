package meal.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.logging.Logger;

@ApplicationScoped
public class ClearDislikedDishesService {

    private static final Logger LOGGER = Logger.getLogger(ClearDislikedDishesService.class.getName());

    @Inject
    UserRepository userRepository;

    @Transactional
    public void clearDislikedDishes(Integer id) {
        try {
            User user = userRepository.findById(id);
            if (user == null) {
                LOGGER.severe("User not found in ClearDislikedDishesService.clearDislikedDishes for ID: " + id);
                throw new IllegalArgumentException("User with ID " + id + " not found");
            }

            user.setDislikedDishes(null);
            userRepository.updateUser(user);
            LOGGER.info("Disliked dishes cleared successfully in ClearDislikedDishesService.clearDislikedDishes for ID: " + id);
        } catch (Exception e) {
            LOGGER.severe("Error in ClearDislikedDishesService.clearDislikedDishes for ID: " + id + ": " + e.getMessage());
            throw new RuntimeException("Failed to clear disliked dishes: " + e.getMessage(), e);
        }
    }
}