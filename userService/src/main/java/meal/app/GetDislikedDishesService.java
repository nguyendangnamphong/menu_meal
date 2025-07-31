package meal.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class GetDislikedDishesService {

    private static final Logger LOGGER = Logger.getLogger(GetDislikedDishesService.class.getName());

    @Inject
    UserRepository userRepository;

    public String getDislikedDishes(Integer id) {
        try {
            User user = userRepository.findById(id);
            if (user == null) {
                LOGGER.severe("User not found in GetDislikedDishesService.getDislikedDishes for ID: " + id);
                throw new IllegalArgumentException("User with ID " + id + " not found");
            }

            String dislikedDishes = user.getDislikedDishes();
            LOGGER.info("Disliked dishes retrieved successfully in GetDislikedDishesService.getDislikedDishes for ID: " + id);
            return dislikedDishes != null ? dislikedDishes : "";
        } catch (Exception e) {
            LOGGER.severe("Error in GetDislikedDishesService.getDislikedDishes for ID: " + id + ": " + e.getMessage());
            throw new RuntimeException("Failed to retrieve disliked dishes: " + e.getMessage(), e);
        }
    }
}