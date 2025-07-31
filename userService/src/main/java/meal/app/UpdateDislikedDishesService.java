package meal.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.logging.Logger;

@ApplicationScoped
public class UpdateDislikedDishesService {

    private static final Logger LOGGER = Logger.getLogger(UpdateDislikedDishesService.class.getName());

    @Inject
    InputValidator inputValidator;

    @Inject
    UserRepository userRepository;

    @Transactional
    public void updateDislikedDishes(Integer id, String dislikedDishes) {
        try {
            if (!inputValidator.validateDislikedDishes(dislikedDishes, "updateDislikedDishes")) {
                LOGGER.severe("Input validation failed in UpdateDislikedDishesService.updateDislikedDishes for ID: " + id);
                throw new IllegalArgumentException("Invalid disliked dishes input");
            }

            User user = userRepository.findById(id);
            if (user == null) {
                LOGGER.severe("User not found in UpdateDislikedDishesService.updateDislikedDishes for ID: " + id);
                throw new IllegalArgumentException("User with ID " + id + " not found");
            }

            user.setDislikedDishes(dislikedDishes);
            userRepository.updateUser(user);
            LOGGER.info("Disliked dishes updated successfully in UpdateDislikedDishesService.updateDislikedDishes for ID: " + id);
        } catch (Exception e) {
            LOGGER.severe("Error in UpdateDislikedDishesService.updateDislikedDishes for ID: " + id + ": " + e.getMessage());
            throw new RuntimeException("Failed to update disliked dishes: " + e.getMessage(), e);
        }
    }
}