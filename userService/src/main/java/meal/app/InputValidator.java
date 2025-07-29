package meal.app;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class InputValidator {

    private static final Logger LOGGER = Logger.getLogger(InputValidator.class.getName());

    public boolean validateUserInput(User user, String methodName) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            LOGGER.severe("Validation failed in " + methodName + ": Name is null or empty");
            return false;
        }
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            LOGGER.severe("Validation failed in " + methodName + ": Role is null or empty");
            return false;
        }
        return true;
    }

    public boolean validateDislikedDishes(String dislikedDishes, String methodName) {
        if (dislikedDishes == null || dislikedDishes.trim().isEmpty()) {
            return true; // Allow null or empty disliked_dishes
        }

        String[] dishIds = dislikedDishes.split(",");
        if (dishIds.length > 5) {
            LOGGER.severe("Validation failed in " + methodName + ": Disliked dishes exceed maximum of 5 IDs");
            return false;
        }

        for (String id : dishIds) {
            try {
                Integer dishId = Integer.parseInt(id.trim());
                if (dishId < 100L || dishId > 999L) {
                    LOGGER.severe("Validation failed in " + methodName + ": Dish ID " + dishId + " is out of range (100-999)");
                    return false;
                }
            } catch (NumberFormatException e) {
                LOGGER.severe("Validation failed in " + methodName + ": Invalid dish ID format - " + id);
                return false;
            }
        }
        return true;
    }
}