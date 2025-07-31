package meal.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/users/{id}/disliked-dishes")
public class ClearDislikedDishesResource {

    private static final Logger LOGGER = Logger.getLogger(ClearDislikedDishesResource.class.getName());

    @Inject
    ClearDislikedDishesService clearDislikedDishesService;

    @DELETE
    public Response clearDislikedDishes(@PathParam("id") Integer id) {
        try {
            clearDislikedDishesService.clearDislikedDishes(id);
            LOGGER.info("Disliked dishes cleared successfully in ClearDislikedDishesResource.clearDislikedDishes for ID: " + id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Error in ClearDislikedDishesResource.clearDislikedDishes for ID: " + id + ": " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User with ID " + id + " not found")
                    .build();
        } catch (Exception e) {
            LOGGER.severe("Error in ClearDislikedDishesResource.clearDislikedDishes for ID: " + id + ": " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to clear disliked dishes: " + e.getMessage())
                    .build();
        }
    }
}