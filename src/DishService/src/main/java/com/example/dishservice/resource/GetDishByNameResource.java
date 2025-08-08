package com.example.dishservice.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/dishes")
@Produces(MediaType.APPLICATION_JSON)
public class GetDishByNameResource {
    private static final Logger LOGGER = Logger.getLogger(GetDishByNameResource.class.getName());

    @GET
    @Path("/{name}")
    public Response getDishByName(@PathParam("name") String name) {
        LOGGER.info("Starting getDishByName method with name: " + name);

        // Validate name
        if (name == null || name.isEmpty() || name.length() > 255) {
            LOGGER.severe("Invalid name: " + name);
            throw new WebApplicationException("Name must not be null, empty, or exceed 255 characters", Response.Status.BAD_REQUEST);
        }

        // Check if dish exists
        try {
            Dish dish = Dish.find("name", name).firstResult();
            if (dish == null) {
                LOGGER.severe("Dish not found for name: " + name);
                throw new WebApplicationException("Dish not found", Response.Status.NOT_FOUND);
            }
            LOGGER.info("Successfully retrieved dish with name: " + name);
            return Response.ok(dish).build();
        } catch (Exception e) {
            LOGGER.severe("Database error while retrieving dish with name " + name + ": " + e.getMessage());
            throw new WebApplicationException("Database error: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}