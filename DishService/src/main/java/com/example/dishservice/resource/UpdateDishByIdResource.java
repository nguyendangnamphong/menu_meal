package com.example.dishservice.resource;

import com.example.dishservice.entity.Dish;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.logging.Logger;

@Path("/dishes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UpdateDishByIdResource {
    private static final Logger LOGGER = Logger.getLogger(UpdateDishByIdResource.class.getName());

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateDishById(@PathParam("id") Integer id, Dish dish) {
        LOGGER.info("Starting updateDishById method with ID: " + id + ", input: " + dish);

        // Validate ID
        if (id == null || id < 100 || id > 999) {
            LOGGER.severe("Invalid ID: " + id);
            throw new WebApplicationException("ID must be between 100 and 999", Response.Status.BAD_REQUEST);
        }

        // Check if dish exists
        Dish existing = Dish.findById(id);
        if (existing == null) {
            LOGGER.severe("Dish not found for ID: " + id);
            throw new WebApplicationException("Dish not found", Response.Status.NOT_FOUND);
        }

        // Validate input
        if (dish.name == null || dish.name.isEmpty() || dish.name.length() > 255) {
            LOGGER.severe("Invalid name: " + dish.name);
            throw new WebApplicationException("Name must not be null, empty, or exceed 255 characters", Response.Status.BAD_REQUEST);
        }
        if (dish.dish_type == null || (!dish.dish_type.equals("đạm") && !dish.dish_type.equals("rau"))) {
            LOGGER.severe("Invalid dish_type: " + dish.dish_type);
            throw new WebApplicationException("dish_type must be 'đạm' or 'rau'", Response.Status.BAD_REQUEST);
        }
        if (dish.time_not_repeat == null || dish.time_not_repeat.compareTo(BigDecimal.ZERO) < 0 || dish.time_not_repeat.compareTo(new BigDecimal("7.00")) > 0) {
            LOGGER.severe("Invalid time_not_repeat: " + dish.time_not_repeat);
            throw new WebApplicationException("time_not_repeat must be between 0 and 7", Response.Status.BAD_REQUEST);
        }
        if (dish.price == null || dish.price.compareTo(BigDecimal.ZERO) < 0) {
            LOGGER.severe("Invalid price: " + dish.price);
            throw new WebApplicationException("price must be non-negative", Response.Status.BAD_REQUEST);
        }

        // Check for name conflict
        Dish existingByName = Dish.find("name", dish.name).firstResult();
        if (existingByName != null && !existingByName.id.equals(id)) {
            LOGGER.severe("Conflict: Name " + dish.name + " already exists with different ID");
            throw new WebApplicationException("Name already exists with different ID", Response.Status.CONFLICT);
        }

        // Update dish
        try {
            existing.name = dish.name;
            existing.dish_type = dish.dish_type;
            existing.time_not_repeat = dish.time_not_repeat;
            existing.price = dish.price;
            existing.persist();
            LOGGER.info("Updated dish successfully: ID " + id);
            return Response.ok(existing).build();
        } catch (Exception e) {
            LOGGER.severe("Database error while updating dish with ID " + id + ": " + e.getMessage());
            throw new WebApplicationException("Database error: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}