package meal.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/users/{id}/disliked-dishes")
public class UpdateDislikedDishesResource {

    private static final Logger LOGGER = Logger.getLogger(UpdateDislikedDishesResource.class.getName());

    @Inject
    InputValidator inputValidator;

    @Inject
    UpdateDislikedDishesService updateDislikedDishesService;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDislikedDishes(@PathParam("id") Integer id, String dislikedDishes) {
        try {
            if (!inputValidator.validateDislikedDishes(dislikedDishes, "updateDislikedDishes")) {
                LOGGER.severe("Input validation failed in UpdateDislikedDishesResource.updateDislikedDishes for ID: " + id);
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid disliked dishes input")
                        .build();
            }

            updateDislikedDishesService.updateDislikedDishes(id, dislikedDishes);
            LOGGER.info("Disliked dishes updated successfully in UpdateDislikedDishesResource.updateDislikedDishes for ID: " + id);
            return Response.status(Response.Status.OK)
                    .entity(new User(id, null, null, dislikedDishes))
                    .build();
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Error in UpdateDislikedDishesResource.updateDislikedDishes for ID: " + id + ": " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User with ID " + id + " not found")
                    .build();
        } catch (Exception e) {
            LOGGER.severe("Error in UpdateDislikedDishesResource.updateDislikedDishes for ID: " + id + ": " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to update disliked dishes: " + e.getMessage())
                    .build();
        }
    }
}