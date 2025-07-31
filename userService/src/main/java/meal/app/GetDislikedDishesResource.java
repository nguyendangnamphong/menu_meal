<<<<<<< HEAD
package meal.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/users/{id}/disliked-dishes")
public class GetDislikedDishesResource {

    private static final Logger LOGGER = Logger.getLogger(GetDislikedDishesResource.class.getName());

    @Inject
    GetDislikedDishesService getDislikedDishesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDislikedDishes(@PathParam("id") Integer id) {
        try {
            String dislikedDishes = getDislikedDishesService.getDislikedDishes(id);
            LOGGER.info("Disliked dishes retrieved successfully in GetDislikedDishesResource.getDislikedDishes for ID: " + id);
            return Response.status(Response.Status.OK)
                    .entity(new User(id, null, null, dislikedDishes))
                    .build();
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Error in GetDislikedDishesResource.getDislikedDishes for ID: " + id + ": " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User with ID " + id + " not found")
                    .build();
        } catch (Exception e) {
            LOGGER.severe("Error in GetDislikedDishesResource.getDislikedDishes for ID: " + id + ": " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to retrieve disliked dishes: " + e.getMessage())
                    .build();
        }
    }
=======
package meal.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/users/{id}/disliked-dishes")
public class GetDislikedDishesResource {

    private static final Logger LOGGER = Logger.getLogger(GetDislikedDishesResource.class.getName());

    @Inject
    GetDislikedDishesService getDislikedDishesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDislikedDishes(@PathParam("id") Integer id) {
        try {
            String dislikedDishes = getDislikedDishesService.getDislikedDishes(id);
            LOGGER.info("Disliked dishes retrieved successfully in GetDislikedDishesResource.getDislikedDishes for ID: " + id);
            return Response.status(Response.Status.OK)
                    .entity(new User(id, null, null, dislikedDishes))
                    .build();
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Error in GetDislikedDishesResource.getDislikedDishes for ID: " + id + ": " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User with ID " + id + " not found")
                    .build();
        } catch (Exception e) {
            LOGGER.severe("Error in GetDislikedDishesResource.getDislikedDishes for ID: " + id + ": " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to retrieve disliked dishes: " + e.getMessage())
                    .build();
        }
    }
>>>>>>> 060e373aa7ea42d5bbb64ecacd8113f262627fb7
}