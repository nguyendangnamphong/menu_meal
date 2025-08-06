package org.acme;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;

import jakarta.inject.Inject;

@Path("/meals")
public class MealSaveResource {

    private final MealSaveExecutor mealSaveExecutor;
    @Inject
    public MealSaveResource(MealSaveExecutor mealSaveExecutor) {
        this.mealSaveExecutor = mealSaveExecutor;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveMeal(Request request) {
        try {
            Response response = mealSaveExecutor.executeSave(request);
            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error saving meal: " + e.getMessage())
                    .build();
        }
    }
}
