package meal.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.logging.Logger;

@ApplicationScoped
public class CreateUserService {

    private static final Logger LOGGER = Logger.getLogger(CreateUserService.class.getName());

    @Inject
    IdGenerator idGenerator;

    @Inject
    InputValidator inputValidator;

    @Inject
    UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        try {
            if (!inputValidator.validateUserInput(user, "createUser")) {
                LOGGER.severe("Input validation failed in CreateUserService.createUser");
                throw new IllegalArgumentException("Invalid user input: name or role is empty");
            }

            user.setId(idGenerator.generateUniqueId());
            userRepository.createUser(user);
            LOGGER.info("User created successfully in CreateUserService.createUser with ID: " + user.getId());
            return user;
        } catch (Exception e) {
            LOGGER.severe("Error in CreateUserService.createUser: " + e.getMessage());
            throw new RuntimeException("Failed to create user: " + e.getMessage(), e);
        }
    }
}