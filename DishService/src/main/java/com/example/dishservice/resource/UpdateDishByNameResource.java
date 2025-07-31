package com.example.dishservice.resource;

import com.example.dishservice.entity.Dish;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.logging.Logger;

@Path("/dishes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UpdateDishByNameResource {
    private static final Logger LOGGER = Logger.getLogger(UpdateDishByNameResource.class.getName());

    @POST
    @Transactional
    public Response updateDishByName(Dish dish) {
        LOGGER.info("Starting updateDishByName method with input: " + dish);

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
        if (dish.id != null) {
            LOGGER.severe("ID provided in input: " + dish.id + ", but update by name should not include ID");
            throw new WebApplicationException("ID must not be provided for update by name", Response.Status.BAD_REQUEST);
        }

        // Check if dish exists by name
        Dish existing = Dish.find("name", dish.name).firstResult();
        if (existing == null) {
            LOGGER.severe("Dish not found for name: " + dish.name);
            throw new WebApplicationException("Dish not found", Response.Status.NOT_FOUND);
        }

        // Update dish
        try {
            existing.dish_type = dish.dish_type;
            existing.time_not_repeat = dish.time_not_repeat;
            existing.price = dish.price;
            existing.persist();
            LOGGER.info("Updated dish successfully: name " + dish.name);
            return Response.ok(existing).build();
        } catch (Exception e) {
            LOGGER.severe("Database error while updating dish with name " + dish.name + ": " + e.getMessage());
            throw new WebApplicationException("Database error: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}