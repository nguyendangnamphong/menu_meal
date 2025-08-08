<<<<<<< HEAD
package meal.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class GetUserRoleService {

    private static final Logger LOGGER = Logger.getLogger(GetUserRoleService.class.getName());

    @Inject
    UserRepository userRepository;

    public String getUserRole(Integer id) {
        try {
            User user = userRepository.findById(id);
            if (user == null) {
                LOGGER.severe("User not found in GetUserRoleService.getUserRole for ID: " + id);
                throw new IllegalArgumentException("User with ID " + id + " not found");
            }

            LOGGER.info("User role retrieved successfully in GetUserRoleService.getUserRole for ID: " + id);
            return user.getRole();
        } catch (Exception e) {
            LOGGER.severe("Error in GetUserRoleService.getUserRole for ID: " + id + ": " + e.getMessage());
            throw new RuntimeException("Failed to retrieve user role: " + e.getMessage(), e);
        }
    }
=======
package meal.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class GetUserRoleService {

    private static final Logger LOGGER = Logger.getLogger(GetUserRoleService.class.getName());

    @Inject
    UserRepository userRepository;

    public String getUserRole(Integer id) {
        try {
            User user = userRepository.findById(id);
            if (user == null) {
                LOGGER.severe("User not found in GetUserRoleService.getUserRole for ID: " + id);
                throw new IllegalArgumentException("User with ID " + id + " not found");
            }

            LOGGER.info("User role retrieved successfully in GetUserRoleService.getUserRole for ID: " + id);
            return user.getRole();
        } catch (Exception e) {
            LOGGER.severe("Error in GetUserRoleService.getUserRole for ID: " + id + ": " + e.getMessage());
            throw new RuntimeException("Failed to retrieve user role: " + e.getMessage(), e);
        }
    }
>>>>>>> 060e373aa7ea42d5bbb64ecacd8113f262627fb7
}