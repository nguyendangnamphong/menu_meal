<<<<<<< HEAD
package meal.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/users")
public class CreateUserResource {

    private static final Logger LOGGER = Logger.getLogger(CreateUserResource.class.getName());

    @Inject
    InputValidator inputValidator;

    @Inject
    CreateUserService createUserService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        try {
            if (!inputValidator.validateUserInput(user, "createUser")) {
                LOGGER.severe("Input validation failed in CreateUserResource.createUser");
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid input: name or role is empty")
                        .build();
            }

            User createdUser = createUserService.createUser(user);
            LOGGER.info("User created successfully in CreateUserResource.createUser with ID: " + createdUser.getId());
            return Response.status(Response.Status.CREATED)
                    .entity(createdUser)
                    .build();
        } catch (Exception e) {
            LOGGER.severe("Error in CreateUserResource.createUser: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to create user: " + e.getMessage())
                    .build();
        }
    }
=======
package meal.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/users")
public class CreateUserResource {

    private static final Logger LOGGER = Logger.getLogger(CreateUserResource.class.getName());

    @Inject
    InputValidator inputValidator;

    @Inject
    CreateUserService createUserService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        try {
            if (!inputValidator.validateUserInput(user, "createUser")) {
                LOGGER.severe("Input validation failed in CreateUserResource.createUser");
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid input: name or role is empty")
                        .build();
            }

            User createdUser = createUserService.createUser(user);
            LOGGER.info("User created successfully in CreateUserResource.createUser with ID: " + createdUser.getId());
            return Response.status(Response.Status.CREATED)
                    .entity(createdUser)
                    .build();
        } catch (Exception e) {
            LOGGER.severe("Error in CreateUserResource.createUser: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to create user: " + e.getMessage())
                    .build();
        }
    }
>>>>>>> 060e373aa7ea42d5bbb64ecacd8113f262627fb7
}