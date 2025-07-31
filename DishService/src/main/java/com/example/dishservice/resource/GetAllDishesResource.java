package com.example.dishservice.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/dishes")
@Produces(MediaType.APPLICATION_JSON)
public class GetAllDishesResource {
    private static final Logger LOGGER = Logger.getLogger(GetAllDishesResource.class.getName());

    @GET
    public Response getAllDishes() {
        LOGGER.info("Starting getAllDishes method");

        try {
            List<Dish> dishes = Dish.listAll();
            LOGGER.info("Successfully retrieved " + dishes.size() + " dishes");
            return Response.ok(dishes).build();
        } catch (Exception e) {
            LOGGER.severe("Database error while retrieving all dishes: " + e.getMessage());
            throw new WebApplicationException("Database error: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}