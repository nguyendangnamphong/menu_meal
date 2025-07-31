package com.example.dishservice.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/dishes")
public class DeleteDishResource {
    private static final Logger LOGGER = Logger.getLogger(DeleteDishResource.class.getName());

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteDish(@PathParam("id") Integer id) {
        LOGGER.info("Starting deleteDish method with ID: " + id);

        // Validate ID
        if (id == null || id < 100 || id > 999) {
            LOGGER.severe("Invalid ID: " + id);
            throw new WebApplicationException("ID must be between 100 and 999", Response.Status.BAD_REQUEST);
        }

        // Check if dish exists
        Dish dish = Dish.findById(id);
        if (dish == null) {
            LOGGER.severe("Dish not found for ID: " + id);
            throw new WebApplicationException("Dish not found", Response.Status.NOT_FOUND);
        }

        // Delete dish
        try {
            dish.delete();
            LOGGER.info("Deleted dish successfully: ID " + id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOGGER.severe("Database error while deleting dish with ID " + id + ": " + e.getMessage());
            throw new WebApplicationException("Database error: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}