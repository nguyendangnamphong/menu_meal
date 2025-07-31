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
public class GetDishByIdResource {
    private static final Logger LOGGER = Logger.getLogger(GetDishByIdResource.class.getName());

    @GET
    @Path("/{id}")
    public Response getDishById(@PathParam("id") Integer id) {
        LOGGER.info("Starting getDishById method with ID: " + id);

        // Validate ID
        if (id == null || id < 100 || id > 999) {
            LOGGER.severe("Invalid ID: " + id);
            throw new WebApplicationException("ID must be between 100 and 999", Response.Status.BAD_REQUEST);
        }

        // Check if dish exists
        try {
            Dish dish = Dish.findById(id);
            if (dish == null) {
                LOGGER.severe("Dish not found for ID: " + id);
                throw new WebApplicationException("Dish not found", Response.Status.NOT_FOUND);
            }
            LOGGER.info("Successfully retrieved dish with ID: " + id);
            return Response.ok(dish).build();
        } catch (Exception e) {
            LOGGER.severe("Database error while retrieving dish with ID " + id + ": " + e.getMessage());
            throw new WebApplicationException("Database error: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}